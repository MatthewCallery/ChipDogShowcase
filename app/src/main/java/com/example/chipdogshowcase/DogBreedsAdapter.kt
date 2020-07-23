package com.example.chipdogshowcase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DogBreedsAdapter : RecyclerView.Adapter<DogBreedsAdapter.ViewHolder>() {
    var data = listOf<DogBreed>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dogBreedName: TextView = itemView.findViewById(R.id.dog_breeds_item_view_name)

        fun bind(item: DogBreed) {
            dogBreedName.text = item.breedName
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.dog_breeds_item_view, parent, false)
                return ViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}