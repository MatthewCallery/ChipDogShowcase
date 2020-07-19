package com.example.chipdogshowcase

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DogBreedsAdapter : RecyclerView.Adapter<DogBreedViewHolder>() {
    var data = listOf<DogBreed>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.breedName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.dog_breeds_item_view, parent, false) as TextView
        return DogBreedViewHolder(view)
    }
}

class DogBreedViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)