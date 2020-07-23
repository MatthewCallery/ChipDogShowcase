package com.example.chipdogshowcase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class DogBreedsViewModel : ViewModel() {
    private val _breeds = MutableLiveData<List<DogBreed>>()
    val breeds: LiveData<List<DogBreed>>
        get() = _breeds

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        //_breeds.value = listOf(DogBreed("Jack Russell"))
        getDogBreeds()
    }

    private fun getDogBreeds() {
        DogApi.retrofitService.getBreeds().enqueue(
            object : retrofit2.Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val jsonObject = JSONObject(response.body())
                    val messageObject = jsonObject.getJSONObject("message")
                    val iter = messageObject.keys()
                    val dogBreedList = arrayListOf<DogBreed>()
                    iter.forEach {
                        val jsonArray: JSONArray = messageObject[it] as JSONArray
                        if (jsonArray.length() > 0) {
                            for (i in 0 until jsonArray.length()) {
                                dogBreedList.add(DogBreed("" + jsonArray[i] + " " + it))
                            }
                        } else {
                            dogBreedList.add(DogBreed(it))
                        }
                    }
                    _breeds.value = dogBreedList
                    //_response.value = response.body()
                }
            }
        )
    }
}