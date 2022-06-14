package com.example.favoritecats.data.network

import com.example.favoritecats.data.room.entities.FavouriteCatDataDb
import kotlinx.serialization.Serializable


@Serializable
data class FavouriteCatData(
    var uid: Long = 0,
    val id: Int = 0,
    var image_id: String = "",
    val sub_id: String? = "",
    var value: Int = 2
) {
    fun toCatDb(): FavouriteCatDataDb = FavouriteCatDataDb(
        id = id,
        imageId = image_id
    )
}
