package com.example.chipdogshowcase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chipdogshowcase.databinding.DogBreedImageItemViewBinding

class DogBreedImagesAdapter : ListAdapter<DogBreedImage, DogBreedImagesAdapter.ViewHolder>(DogBreedImagesDiffCallback()) {

    class ViewHolder private constructor(private val binding: DogBreedImageItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DogBreedImage) {
            binding.dog = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DogBreedImageItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}

class DogBreedImagesDiffCallback : DiffUtil.ItemCallback<DogBreedImage>() {
    override fun areItemsTheSame(oldItem: DogBreedImage, newItem: DogBreedImage): Boolean {
        return oldItem.imageURL == newItem.imageURL
    }

    override fun areContentsTheSame(oldItem: DogBreedImage, newItem: DogBreedImage): Boolean {
        return oldItem == newItem
    }
}