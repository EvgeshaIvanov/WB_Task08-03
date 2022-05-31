package com.example.favoritecats.network

import android.util.Log
import com.example.favoritecats.model.CatData
import com.example.favoritecats.model.FavouriteCatData
import com.example.favoritecats.model.ImageCat
import com.example.favoritecats.network.KtorInstance.Companion.BASE_URL
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

object RepositoryImpl {

    private val apiRequest = KtorInstance()

    suspend fun getRandomCat(): List<CatData> {
        return apiRequest.getClient().get("$BASE_URL/images/search").body()
    }

    @OptIn(InternalAPI::class)
    suspend fun likeCat(imageId: String, value: Int, subId: String): FavouriteCatData {
        return apiRequest.getClient().post("$BASE_URL/votes") {
            contentType(ContentType.Application.Json)
            setBody(FavouriteCatData(image_id = imageId, sub_id = subId, value = value))
        }.body<FavouriteCatData>()
    }

    suspend fun getFavouriteCats(): List<FavouriteCatData> {
        return apiRequest.getClient().get("$BASE_URL/votes"){
            parameter("sub_id", "levi-2708")
            parameter("value", 0)
        }.body()
    }

    suspend fun getImageFromId(imageId: String) : ImageCat {
        Log.i("REQS", imageId)
            return apiRequest.getClient().get("$BASE_URL/images/$imageId").body()
    }

    suspend fun deleteFromFavouriteList(id: Int) {
        return apiRequest.getClient().delete("$BASE_URL/votes/$id").body()
    }

    /*
    override suspend fun getRandomCat(): List<CatData> {
        var cat: List<CatData> = mutableListOf()
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
            cat = client.get("https://api.thecatapi.com/v1/images/search"){
                header("x-api-key", "b8ba2d27-bdee-4f70-bcf5-339a73a5e1fd")
            }.body()
            cat.forEach {
                Log.i("SEARCH_LIST", "${it.id}, ${it.url}")
            }
        return cat.toList()
    }
     */
}