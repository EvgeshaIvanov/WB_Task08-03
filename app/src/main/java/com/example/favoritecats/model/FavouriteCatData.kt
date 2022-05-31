package com.example.favoritecats.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

//TODO id для получения фаворитом на приеме интовый!!!
@Serializable
data class FavouriteCatData(
    val image_id: String = "MTYzNzQwMA",
   // val sub_id: String = "levi-2708",
    val value: Int = 1
)


fun main() {
    print(Json.encodeToJsonElement(FavouriteCatData("dawda",  2)))
}