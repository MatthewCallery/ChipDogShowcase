package com.example.chipdogshowcase.dogbreeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chipdogshowcase.DogBreed
import com.example.chipdogshowcase.databinding.DogBreedsItemViewBinding

class DogBreedsAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<DogBreed, DogBreedsAdapter.ViewHolder>(
        DogBreedsDiffCallback()
    ) {

    class ViewHolder private constructor(private val binding: DogBreedsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DogBreed) {
            binding.dogBreed = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DogBreedsItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    class OnClickListener(val clickListener: (dogBreed: DogBreed) -> Unit) {
        fun onClick(dogBreed: DogBreed) = clickListener(dogBreed)
    }
}

class DogBreedsDiffCallback : DiffUtil.ItemCallback<DogBreed>() {
    override fun areItemsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
        return oldItem.breedName == newItem.breedName
    }

    override fun areContentsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
        return oldItem == newItem
    }
}