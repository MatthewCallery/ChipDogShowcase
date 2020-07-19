package com.example.chipdogshowcase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DogBreedsViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogBreedsViewModel::class.java)) {
            return DogBreedsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

