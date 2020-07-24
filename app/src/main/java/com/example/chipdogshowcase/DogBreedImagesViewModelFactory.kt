package com.example.chipdogshowcase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DogBreedImagesViewModelFactory(private val breed: DogBreed) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogBreedImagesViewModel::class.java)) {
            return DogBreedImagesViewModel(breed) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}