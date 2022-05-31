package com.example.favoritecats.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritecats.databinding.FragmentFavouritesCatsBinding
import com.example.favoritecats.databinding.ItemListBinding
import com.example.favoritecats.model.FavouriteCatData

class CatsAdapter: RecyclerView.Adapter<CatsAdapter.CatsViewHolder>() {

    var cats = emptyList<FavouriteCatData>()
    @SuppressLint("NotifyDataSetChanged")
    set(value){
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return CatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val cat = cats[position]
        holder.binding.imageId.text = cat.image_id
    }

    override fun getItemCount(): Int = cats.size


    class CatsViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {

    }

}