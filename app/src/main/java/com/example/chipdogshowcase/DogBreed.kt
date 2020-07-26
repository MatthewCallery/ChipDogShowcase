package com.example.chipdogshowcase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import org.json.JSONObject

@Parcelize
data class DogBreed(val breedName: String = "", val imageUrls: ArrayList<String> = arrayListOf()) : Parcelable

// Convert network result to DogBreed objects
fun String.asDogBreedList() : ArrayList<DogBreed> {
    val dogBreedList = arrayListOf<DogBreed>()

    if (this.isEmpty()) { return dogBreedList }

    val messageObject = JSONObject(this).getJSONObject("message")
    // messageObject keys are dog breed names
    messageObject.keys().forEach {
        val subBreedArray: JSONArray = messageObject[it] as JSONArray
        if (subBreedArray.length() > 0) {
            for (i in 0 until subBreedArray.length()) {
                val subBreed = (subBreedArray[i] as String).capitalize()
                dogBreedList.add(DogBreed("$subBreed ${it.capitalize()}"))
            }
        } else {
            dogBreedList.add(DogBreed(it.capitalize()))
        }
    }
    return dogBreedList
}

// Convert network result to list of image URL strings
fun String.asDogImageUrlList() : ArrayList<String> {
    val messageArray = JSONObject(this).getJSONArray("message")
    val imageList = arrayListOf<String>()

    for (i in 0 until messageArray.length()) {
        imageList.add(messageArray[i] as String)
    }
    return imageList
}

// Add list of image URLs to DogBreed object
fun DogBreed.addImageUrlList(imageList: ArrayList<String>) {
    this.imageUrls.clear()
    this.imageUrls.addAll(imageList)
}