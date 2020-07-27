package com.example.chipdogshowcase.dogimages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chipdogshowcase.DogBreed
import com.example.chipdogshowcase.Repository
import java.lang.IllegalArgumentException

class DogImagesViewModelFactory(private val breed: DogBreed, private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogImagesViewModel::class.java)) {
            return DogImagesViewModel(
                breed, repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}