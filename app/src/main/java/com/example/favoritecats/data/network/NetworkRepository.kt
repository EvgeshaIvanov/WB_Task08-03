package com.example.favoritecats.data.network

import com.example.favoritecats.data.network.KtorInstance.Companion.BASE_URL
import com.example.favoritecats.data.network.KtorInstance.Companion.SUB_ID
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

object NetworkRepository {

    private val apiRequest = KtorInstance()

    suspend fun getRandomCat(): List<CatData> {
        return apiRequest.getClient().get("$BASE_URL/images/search").body()
    }

    suspend fun likeCat(imageId: String, value: Int, subId: String): FavouriteCatData {
        return apiRequest.getClient().post("$BASE_URL/votes") {
            contentType(ContentType.Application.Json)
            setBody(FavouriteCatData(image_id = imageId, sub_id = subId, value = value))
        }.body()
    }

    suspend fun getFavouriteCats(): List<FavouriteCatData> {
        return apiRequest.getClient().get("$BASE_URL/votes") {
            parameter("sub_id", SUB_ID)
            parameter("value", 0)
        }.body()
    }

    suspend fun deleteFromFavouriteList(id: Int) {
        return apiRequest.getClient().delete("$BASE_URL/votes/$id").body()
    }

}