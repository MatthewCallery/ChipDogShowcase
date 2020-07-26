package com.example.chipdogshowcase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import org.json.JSONObject

@Parcelize
data class DogBreed(val breedName: String = "") : Parcelable

// Convert network result to DogBreed objects
fun String.asDogBreedList() : ArrayList<DogBreed> {
    val messageObject = JSONObject(this).getJSONObject("message")
    val dogBreedList = arrayListOf<DogBreed>()

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