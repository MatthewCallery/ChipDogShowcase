package com.example.chipdogshowcase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import org.json.JSONObject

@Parcelize
data class DogBreed(val breedName: String = "") : Parcelable

fun String.asDogBreedList() : ArrayList<DogBreed> {
    val jsonObject = JSONObject(this)
    val messageObject = jsonObject.getJSONObject("message")
    val dogBreedList = arrayListOf<DogBreed>()

    messageObject.keys().forEach {
        val subBreedArray: JSONArray = messageObject[it] as JSONArray
        if (subBreedArray.length() > 0) {
            for (i in 0 until subBreedArray.length()) {
                dogBreedList.add(DogBreed("${(subBreedArray[i] as String).capitaliseWords()} ${it.capitaliseWords()}"))
            }
        } else {
            dogBreedList.add(DogBreed(it.capitaliseWords()))
        }
    }
    return dogBreedList
}