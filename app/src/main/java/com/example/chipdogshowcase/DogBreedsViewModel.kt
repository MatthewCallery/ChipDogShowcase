package com.example.chipdogshowcase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DogBreedsViewModel : ViewModel() {
    private val _breeds = MutableLiveData<List<DogBreed>>()
    val breeds: LiveData<List<DogBreed>>
        get() = _breeds

    private val _status = MutableLiveData<DogApiStatus>()
    val status: LiveData<DogApiStatus>
        get() = _status

    private val _navigateToSelectedProperty = MutableLiveData<DogBreed>()
    val navigateToSelectedProperty: LiveData<DogBreed>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getDogBreeds()
    }

    fun displayDogBreedImages(dogBreed: DogBreed) {
        _navigateToSelectedProperty.value = dogBreed
    }

    fun displayDogBreedImagesComplete() {
        _navigateToSelectedProperty.value = null
    }

    private fun getDogBreeds() {
        coroutineScope.launch {
            try {
                _status.value = DogApiStatus.LOADING
                val getDogBreedsDeferred = DogApi.retrofitService.getBreedsAsync()
                _status.value = DogApiStatus.DONE
                _breeds.value = getDogBreedsDeferred.asDogBreedList()
            } catch (e: Exception) {
                _status.value = DogApiStatus.ERROR
                _breeds.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}