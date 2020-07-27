package com.example.chipdogshowcase.dogbreeds

import androidx.recyclerview.widget.DiffUtil
import com.example.chipdogshowcase.DogBreed

class DogBreedsDiffCallback : DiffUtil.ItemCallback<DogBreed>() {
    override fun areItemsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
        return oldItem.breedName == newItem.breedName
    }

    override fun areContentsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
        return oldItem == newItem
    }
}