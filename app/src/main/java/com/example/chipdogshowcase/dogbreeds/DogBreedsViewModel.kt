package com.example.chipdogshowcase.dogbreeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chipdogshowcase.network.DogApiStatus
import com.example.chipdogshowcase.DogBreed
import com.example.chipdogshowcase.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DogBreedsViewModel(private val repository: Repository) : ViewModel() {
    val breeds: LiveData<List<DogBreed>> = repository.dogBreeds

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
        refreshDogBreedDataFromRepository()
    }

    fun displayDogBreedImages(dogBreed: DogBreed) {
        _navigateToSelectedProperty.value = dogBreed
    }

    fun displayDogBreedImagesComplete() {
        _navigateToSelectedProperty.value = null
    }

    private fun refreshDogBreedDataFromRepository() {
        coroutineScope.launch {
            try {
                if (breeds.value.isNullOrEmpty()) {
                    _status.value = DogApiStatus.LOADING
                }
                repository.refreshDogBreeds()
                _status.value = DogApiStatus.DONE
            } catch (e: Exception) {
                if (breeds.value.isNullOrEmpty()) {
                    _status.value = DogApiStatus.ERROR
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}