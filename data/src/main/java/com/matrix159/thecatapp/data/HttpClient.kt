package com.matrix159.thecatapp.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import timber.log.Timber

private const val TIME_OUT = 60 * 1000 // 2 minute request timeout

@OptIn(ExperimentalSerializationApi::class)
internal fun createCatApiClient(apiKey: String) = HttpClient {

    install(HttpTimeout) {
        requestTimeoutMillis = TIME_OUT.toLong()
    }

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        })
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Timber.d("Logger Ktor => $message")
            }

        }
        level = LogLevel.INFO
    }

    install(ResponseObserver) {
        onResponse { response ->
            Timber.d("HTTP status: ${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        header("x-api-key", apiKey)
    }

    install(HttpCache)
}


