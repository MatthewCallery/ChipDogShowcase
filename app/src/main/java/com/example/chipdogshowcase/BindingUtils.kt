package com.example.chipdogshowcase

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("dogBreedName")
fun TextView.setDogBreedName(item: DogBreed) {
    text = item.breedName
}