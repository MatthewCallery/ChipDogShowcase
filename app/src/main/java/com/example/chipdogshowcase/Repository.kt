package com.example.chipdogshowcase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chipdogshowcase.network.DogApi
import com.example.chipdogshowcase.network.DogApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val sharedPrefService: SavedPreferencesService) {
    val dogBreeds : LiveData<List<DogBreed>> = sharedPrefService

    suspend fun refreshDogBreeds() {
        withContext(Dispatchers.IO) {
            val dogBreeds = DogApi.retrofitService.getBreedsAsync()
            sharedPrefService.saveDogBreedList(dogBreeds)
        }
    }
}