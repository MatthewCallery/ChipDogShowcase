package com.example.chipdogshowcase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DogBreedsViewModel : ViewModel() {
    private val _breeds = MutableLiveData<List<DogBreed>>()
    val breeds: LiveData<List<DogBreed>>
        get() = _breeds

    init {
        _breeds.value = listOf(DogBreed("Jack Russell"))
    }
}