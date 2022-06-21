package com.example.favoritecats.data.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json

class KtorInstance {

    private val client = HttpClient(CIO) {

        expectSuccess = true

        install(DefaultRequest) {
            header(API_KEY, TOKEN)
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP status: ${response.status.value}")
            }
        }
        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val statusCode = response.status.value
                val responseCall = response.call.response.toString()
                println("Response Call $responseCall")
                println("HTTP status: $statusCode")

                when (statusCode) {
                    in 300..399 -> throw RedirectResponseException(response, responseCall)
                    in 400..499 -> throw ClientRequestException(response, responseCall)
                    in 500..599 -> throw ServerResponseException(response, responseCall)
                }

                if (statusCode >= 600) {
                    throw ResponseException(response, responseCall)
                }
            }

            handleResponseExceptionWithRequest { cause: Throwable, _ ->
                throw cause
            }
        }
    }

    fun getClient(): HttpClient {
        return client
    }

    companion object {
        const val LIKE = 0
        const val SUB_ID = "levi-2708"
        const val BASE_URL = "https://api.thecatapi.com/v1"
        const val API_KEY = "x-api-key"
        const val TOKEN = "834fffcf-e5fc-46fb-af03-6ebd407683b1"
    }

}