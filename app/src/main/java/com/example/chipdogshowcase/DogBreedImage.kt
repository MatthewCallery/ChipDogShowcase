package com.example.chipdogshowcase

import org.json.JSONObject

data class DogBreedImage(val imageURL: String = "")

fun String.asDogBreedImageUrlList() : ArrayList<DogBreedImage> {
    val messageArray = JSONObject(this).getJSONArray("message")
    val imageList = arrayListOf<DogBreedImage>()

    for (i in 0 until messageArray.length()) {
        imageList.add(DogBreedImage(messageArray[i] as String))
    }
    return imageList
}