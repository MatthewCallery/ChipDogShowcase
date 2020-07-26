package com.example.chipdogshowcase.dogbreedimages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chipdogshowcase.network.DogApi
import com.example.chipdogshowcase.network.DogApiStatus
import com.example.chipdogshowcase.DogBreed
import com.example.chipdogshowcase.addImageUrlList
import com.example.chipdogshowcase.asDogImageUrlList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DogBreedImagesViewModel(private var breed: DogBreed) : ViewModel() {
    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>>
        get() = _images

    private val _status = MutableLiveData<DogApiStatus>()
    val status: LiveData<DogApiStatus>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getDogImages()
    }

    private fun getDogImages() {
        coroutineScope.launch {
            try {
                _status.value = DogApiStatus.LOADING
                val cleanedBreedName = cleanBreedName(breed.breedName)
                val getDogImagesDeferred = DogApi.retrofitService.getDogImagesAsync(cleanedBreedName)
                _status.value = DogApiStatus.DONE
                breed.addImageUrlList(getDogImagesDeferred.asDogImageUrlList())
                _images.value = breed.imageUrls
            } catch (e: Exception) {
                _status.value = DogApiStatus.ERROR
                _images.value = ArrayList()
            }
        }
    }

    private fun cleanBreedName(breedName: String) : String {
        val nameArray = breedName.split(" ")
        var cleanedBreedName = ""
        if (nameArray.size == 1) {
            cleanedBreedName = nameArray[0].toLowerCase()
        } else if (nameArray.size == 2) {
            cleanedBreedName = "${nameArray[1].toLowerCase()}/${nameArray[0].toLowerCase()}"
        }
        return cleanedBreedName
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}