package com.example.chipdogshowcase

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("dogBreedName")
fun TextView.setDogBreedName(item: DogBreed) {
    text = item.breedName
}

// Init DogBreedsAdapter with list of DogBreed objects
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DogBreed>?) {
    val adapter = recyclerView.adapter as DogBreedsAdapter
    adapter.submitList(data)
}

// Take URL from an XML attribute associated with an ImageView, and use Glide to load the image
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}