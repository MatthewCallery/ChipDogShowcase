package com.example.chipdogshowcase

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("dogBreedName")
fun TextView.setDogBreedName(item: DogBreed) {
    text = item.breedName
}

// Init DogBreedsAdapter with list of DogBreed objects
@BindingAdapter("listData")
fun bindDogBreedRecyclerView(recyclerView: RecyclerView, data: List<DogBreed>?) {
    val adapter = recyclerView.adapter as DogBreedsAdapter
    adapter.submitList(data)
}

// Init DogBreedImagesAdapter with list of DogBreedImage objects
@BindingAdapter("imageListData")
fun bindDogBreedImageRecyclerView(recyclerView: RecyclerView, data: List<DogBreedImage>?) {
    val adapter = recyclerView.adapter as DogBreedImagesAdapter
    adapter.submitList(data)
}

// Take URL from an XML attribute associated with an ImageView, and use Glide to load the image
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.dog_icon))
            .into(imgView)
    }
}

@BindingAdapter("dogApiStatus")
fun bindStatus(statusImageView: ImageView, status: DogApiStatus?) {
    when (status) {
        DogApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        DogApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DogApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}