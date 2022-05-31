package com.example.favoritecats.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritecats.databinding.FragmentFavouritesCatsBinding
import com.example.favoritecats.databinding.ItemListBinding
import com.example.favoritecats.model.FavouriteCatData
import com.example.favoritecats.model.ImageCat
import com.facebook.drawee.backends.pipeline.Fresco

class CatsAdapter: RecyclerView.Adapter<CatsAdapter.CatsViewHolder>() {
    var clickOnCatListener: ((FavouriteCatData) -> Unit)? = null
    var cats = emptyList<FavouriteCatData>()
    @SuppressLint("NotifyDataSetChanged")
    set(value){
        val callBack = CatsListDiffCallBack(cats, value)
        val diffResult = DiffUtil.calculateDiff(callBack)
        diffResult.dispatchUpdatesTo(this)
        field = value

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return CatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val cat = cats[position]
        holder.binding.imageId.text = cat.image_id
        val controller = Fresco.newDraweeControllerBuilder()
            .setUri("https://cdn2.thecatapi.com/images/${cat.image_id}.jpg")
            .build()
        holder.binding.imageCat.controller = controller
        holder.binding.deleteButton.setOnClickListener {
            clickOnCatListener!!.invoke(cat)
        }

    }

    override fun getItemCount(): Int = cats.size


    class CatsViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {

    }

}