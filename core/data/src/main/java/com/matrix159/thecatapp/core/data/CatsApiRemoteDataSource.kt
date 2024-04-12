package com.matrix159.thecatapp.core.data

import com.matrix159.thecatapp.core.data.model.ApiBreed
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class CatsApiRemoteDataSource(
  private val httpClient: HttpClient
) : CatsRemoteDataSource {
  private val baseUrl = "https://api.thecatapi.com/v1"
  override suspend fun getBreeds(): List<ApiBreed> {
    return httpClient.get("$baseUrl/breeds").body<List<ApiBreed>>()
  }
}