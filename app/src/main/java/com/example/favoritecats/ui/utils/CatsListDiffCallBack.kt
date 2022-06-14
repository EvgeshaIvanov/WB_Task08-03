package com.example.favoritecats.ui.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.favoritecats.data.network.FavouriteCatData

class CatsListDiffCallBack(
    private val oldList: List<FavouriteCatData>,
    private val newList: List<FavouriteCatData>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList =  oldList[oldItemPosition]
        val newList =  newList[newItemPosition]
        return oldList.image_id == newList.image_id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList =  oldList[oldItemPosition]
        val newList =  newList[newItemPosition]
        return oldList == newList
    }
}