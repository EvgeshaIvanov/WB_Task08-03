package com.example.favoritecats.data.network

import kotlinx.serialization.Serializable


@Serializable
data class CatData(
    val id: String,
    val url: String,
)


