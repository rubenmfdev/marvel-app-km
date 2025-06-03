package com.example.myapplication.utils.Network

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.DeserializationStrategy
import io.ktor.serialization.kotlinx.json.*
import com.example.myapplication.utils.ErrorEntity

interface NetworkManager {
    suspend fun <T : Any> requestObject(
        router: BaseRouter,
        responseType: DeserializationStrategy<T>
    ): Result<T>
}

class KtorNetworkManager(
    private val client: HttpClient = defaultHttpClient()
) : NetworkManager {

    override suspend fun <T : Any> requestObject(
        router: BaseRouter,
        responseType: DeserializationStrategy<T>
    ): Result<T> {
        return withContext(Dispatchers.Default) {
            try {
                val response: HttpResponse = client.request {
                    method = router.method
                    url(router.fullUrl)
                    headers {
                        router.headers.forEach { (key, value) -> append(key, value) }
                    }
                    router.body?.let {
                        setBody(it)
                    }
                }

                val responseBody = Json.decodeFromString(responseType, response.bodyAsText())

                Result.success(responseBody)
            } catch (e: Exception) {
                Result.failure(ErrorEntity.Generic)
            }
        }
    }
}

fun defaultHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        expectSuccess = false
    }
}