package com.example.chipdogshowcase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class DogBreedImagesViewModel(breed: DogBreed) : ViewModel() {
    private val _images = MutableLiveData<List<DogBreedImage>>()
    val images: LiveData<List<DogBreedImage>>
        get() = _images

    private val _status = MutableLiveData<DogApiStatus>()
    val status: LiveData<DogApiStatus>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        Log.i("BreedName", breed.breedName)
        val nameArray = breed.breedName.split(" ")
        var cleanedBreedString = ""
        if (nameArray.size == 1) {
            cleanedBreedString = nameArray[0].toLowerCase()
        } else if (nameArray.size == 2) {
            cleanedBreedString = "${nameArray[1].toLowerCase()}/${nameArray[0].toLowerCase()}"
        }

        getDogImages(cleanedBreedString)
    }

    private fun getDogImages(breed: String) {
        coroutineScope.launch {
            try {
                _status.value = DogApiStatus.LOADING
                Log.i("url", "breed/${breed}/images")
                val getDogImagesDeferred = DogApi.retrofitService.getDogImagesAsync(breed)
                _status.value = DogApiStatus.DONE
                _images.value = convertDogBreedImagesJsonString(getDogImagesDeferred)
            } catch (e: Exception) {
                _status.value = DogApiStatus.ERROR
                _images.value = ArrayList()
                Log.i("Error", "${e.message}")
            }
        }
    }

    private fun convertDogBreedImagesJsonString(jsonString: String): ArrayList<DogBreedImage> {
        val jsonObject = JSONObject(jsonString)
        val messageArray = jsonObject.getJSONArray("message")
        val imageList = arrayListOf<DogBreedImage>()

        for (i in 0 until messageArray.length()) {
            imageList.add(DogBreedImage(messageArray[i] as String))
        }
        return imageList
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}