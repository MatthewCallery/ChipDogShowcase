package com.example.chipdogshowcase.dogbreeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chipdogshowcase.repository.Repository
import java.lang.IllegalArgumentException

class DogBreedsViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogBreedsViewModel::class.java)) {
            return DogBreedsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

