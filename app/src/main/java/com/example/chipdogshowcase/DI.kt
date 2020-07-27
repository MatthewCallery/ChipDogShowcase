package com.example.chipdogshowcase

import com.example.chipdogshowcase.network.DogApiService
import com.example.chipdogshowcase.network.retrofit
import com.example.chipdogshowcase.repository.Repository
import com.example.chipdogshowcase.sharedpref.SharedPreferencesService

// Dependency Injection object containing singletons
object DI {
    private val sharedPreferencesService =
        SharedPreferencesService(
            FetchApplication.appContext
        )
    private val retrofitService: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }
    val repository = Repository(
        sharedPreferencesService,
        retrofitService
    )
}