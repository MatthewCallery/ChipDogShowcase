package com.example.chipdogshowcase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

enum class DogApiStatus { LOADING, ERROR, DONE }

class DogBreedsViewModel : ViewModel() {
    private val _breeds = MutableLiveData<List<DogBreed>>()
    val breeds: LiveData<List<DogBreed>>
        get() = _breeds

    private val _status = MutableLiveData<DogApiStatus>()
    val status: LiveData<DogApiStatus>
        get() = _status

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
                _status.value = DogApiStatus.LOADING
                val getDogBreedsDeferred = DogApi.retrofitService.getBreedsAsync()
                _status.value = DogApiStatus.DONE
                _breeds.value = convertDogBreedsJsonString(getDogBreedsDeferred)
            } catch (e: Exception) {
                //TODO Error Handling
                _status.value = DogApiStatus.ERROR
                _breeds.value = ArrayList()
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