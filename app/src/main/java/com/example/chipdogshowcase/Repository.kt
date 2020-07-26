package com.example.chipdogshowcase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chipdogshowcase.network.DogApi
import com.example.chipdogshowcase.network.DogApiService
import com.example.chipdogshowcase.network.DogApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class Repository(private val sharedPrefService: SavedPreferencesService, private val dogApiService: DogApiService) {
    val dogBreeds : LiveData<List<DogBreed>> = sharedPrefService

    suspend fun refreshDogBreeds() {
        withContext(Dispatchers.IO) {
            val dogBreeds = dogApiService.getBreedsAsync()
            sharedPrefService.saveDogBreedList(dogBreeds)
        }
    }
}