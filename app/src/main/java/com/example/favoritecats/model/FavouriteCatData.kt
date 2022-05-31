package com.example.favoritecats.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

//TODO id для получения фаворитом на приеме интовый!!!
@Serializable
data class FavouriteCatData(
    val id: Int = 0,
    var image_id: String = "",
    val sub_id: String? = "",
    var value: Int = 2
)


fun main() {
    //print(Json.encodeToJsonElement(FavouriteCatData("dawda",  2)))
}