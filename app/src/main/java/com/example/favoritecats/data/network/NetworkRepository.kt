package com.example.favoritecats.data.network

import com.example.favoritecats.data.network.KtorInstance.Companion.BASE_URL
import com.example.favoritecats.data.network.KtorInstance.Companion.SUB_ID
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

object NetworkRepository {

    private val apiRequest = KtorInstance()

    suspend fun getRandomCat(): List<CatData> {
        return try {
            apiRequest.getClient().get("$BASE_URL/images/search").body()
        } catch (se: ServerResponseException) {
            println("HTTP status code $se --> ${se.message}")
            emptyList()
        } catch (ce: ClientRequestException) {
            println("HTTP status code $ce --> ${ce.message}")
            emptyList()
        } catch (re: RedirectResponseException) {
            println("HTTP status code $re --> ${re.message}")
            emptyList()
        }
    }

    suspend fun likeCat(imageId: String, value: Int, subId: String): FavouriteCatData? {
        return try {
            apiRequest.getClient().post("$BASE_URL/votes") {
                contentType(ContentType.Application.Json)
                setBody(FavouriteCatData(image_id = imageId, sub_id = subId, value = value))
            }.body()
        } catch (ce: ClientRequestException) {
            println("HTTP status code $ce --> ${ce.message}")
            return null
        }
    }

    suspend fun getFavouriteCats(): List<FavouriteCatData> {
        return try {
            apiRequest.getClient().get("$BASE_URL/votes") {
                parameter("sub_id", SUB_ID)
                parameter("value", 0)
            }.body()
        } catch (se: ServerResponseException) {
            println("HTTP status code $se --> ${se.message}")
            emptyList()
        } catch (ce: ClientRequestException) {
            println("HTTP status code $ce --> ${ce.message}")
            emptyList()
        } catch (re: RedirectResponseException) {
            println("HTTP status code $re --> ${re.message}")
            emptyList()
        }
    }

    suspend fun deleteFromFavouriteList(id: Int) {
        return try {
            apiRequest.getClient().delete("$BASE_URL/votes/$id").body()
        } catch (ce: ClientRequestException) {
            println("HTTP status code $ce --> ${ce.message}")
        }
    }

}