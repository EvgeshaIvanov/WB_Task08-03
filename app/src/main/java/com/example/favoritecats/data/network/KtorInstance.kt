package com.example.favoritecats.data.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*

import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorInstance {

    private val client = HttpClient(CIO) {
        install(DefaultRequest) {
            header(API_KEY, TOKEN)
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    fun getClient(): HttpClient{
        return client
    }

    companion object {
        const val LIKE = 0
        const val DISLIKE = 1
        const val SUB_ID = "levi-2708"
        const val BASE_URL = "https://api.thecatapi.com/v1"
        const val API_KEY = "x-api-key"
        const val TOKEN = "834fffcf-e5fc-46fb-af03-6ebd407683b1"
    }

}