package com.example.favoritecats.data.room.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.favoritecats.data.network.FavouriteCatData

@Entity(
    tableName = "favourite_cats",
    indices = [Index(value = ["id"], unique = true)]
)
data class FavouriteCatDataDb(
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0,
    val id: Int = 0,
    var imageId: String = "",
) {
    fun toCat(): FavouriteCatData = FavouriteCatData(
        image_id = imageId,
        uid = uid,
        id = id
    )
}
