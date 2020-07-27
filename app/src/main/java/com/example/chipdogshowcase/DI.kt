package com.example.chipdogshowcase

import com.example.chipdogshowcase.network.DogApiService
import com.example.chipdogshowcase.network.retrofit

// Dependency Injection object containing singletons
object DI {
    private val savedPreferencesService = SavedPreferencesService(MyApplication.appContext)
    private val retrofitService: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }
    val repository = Repository(savedPreferencesService, retrofitService)
}