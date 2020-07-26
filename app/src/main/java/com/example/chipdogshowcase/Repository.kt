package com.example.chipdogshowcase

import com.example.chipdogshowcase.network.DogApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {
    suspend fun refreshDogBreeds() {
        withContext(Dispatchers.IO) {
            val dogBreeds = DogApi.retrofitService.getBreedsAsync()
            //TODO insert list into persistent storage
        }
    }
}