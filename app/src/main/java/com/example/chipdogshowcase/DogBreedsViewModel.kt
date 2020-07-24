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

class DogBreedsViewModel : ViewModel() {
    private val _breeds = MutableLiveData<List<DogBreed>>()
    val breeds: LiveData<List<DogBreed>>
        get() = _breeds

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getDogBreeds()
    }

    private fun getDogBreeds() {
        coroutineScope.launch {
            try {
                val getDogBreedsDeferred = DogApi.retrofitService.getBreedsAsync()
                _response.value = "Success: Dog breeds retrieved"
                Log.i("Success", _response.value)
                _breeds.value = convertDogBreedsJsonString(getDogBreedsDeferred)
            } catch (e: Exception) {
                //TODO Error Handling
                _response.value = "Error: ${e.message}"
                Log.i("Error", _response.value)
            }
        }
    }

    private fun convertDogBreedsJsonString(jsonString: String): ArrayList<DogBreed> {
        val jsonObject = JSONObject(jsonString)
        val messageObject = jsonObject.getJSONObject("message")
        val dogBreedList = arrayListOf<DogBreed>()

        messageObject.keys().forEach {
            val subBreedArray: JSONArray = messageObject[it] as JSONArray
            if (subBreedArray.length() > 0) {
                for (i in 0 until subBreedArray.length()) {
                    dogBreedList.add(DogBreed("${(subBreedArray[i] as String).capitaliseWords()} ${it.capitaliseWords()}"))
                }
            } else {
                dogBreedList.add(DogBreed(it.capitaliseWords()))
            }
        }
        return dogBreedList
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}