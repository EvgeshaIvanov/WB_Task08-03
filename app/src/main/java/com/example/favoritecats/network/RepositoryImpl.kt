package com.example.favoritecats.network

import com.example.favoritecats.model.CatData
import com.example.favoritecats.model.FavouriteCatData
import com.example.favoritecats.network.KtorInstance.Companion.BASE_URL
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

object RepositoryImpl {

    private val apiRequest = KtorInstance()

    suspend fun getRandomCat(): List<CatData> {
        return apiRequest.getClient().get("$BASE_URL/images/search").body()
    }

    @OptIn(InternalAPI::class)
    suspend fun likeCat(imageId: String): FavouriteCatData {
        return apiRequest.getClient().post("$BASE_URL/votes") {
            contentType(ContentType.Application.Json)
            setBody(FavouriteCatData(image_id = imageId, value = 1))
        }.body<FavouriteCatData>()
    }

    suspend fun getFavouriteCats(): List<FavouriteCatData> {
        return apiRequest.getClient().get("$BASE_URL/votes").body()
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