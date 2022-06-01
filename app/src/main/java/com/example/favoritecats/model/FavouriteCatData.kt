package com.example.favoritecats.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement


@Serializable
data class FavouriteCatData(
    val id: Int = 0,
    var image_id: String = "",
    val sub_id: String? = "",
    var value: Int = 2
)
