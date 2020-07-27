package com.example.chipdogshowcase.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dog.ceo/api/"

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DogApiService {
    @GET("breeds/list/all")
    suspend fun getBreedsAsync():
            String

    @GET("breed/{breed_name}/images/random/10")
    suspend fun getDogImagesAsync(@Path("breed_name", encoded = true) name: String):
            String
}