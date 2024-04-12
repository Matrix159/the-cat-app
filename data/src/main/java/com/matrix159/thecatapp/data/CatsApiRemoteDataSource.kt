package com.matrix159.thecatapp.data

import io.ktor.client.HttpClient

internal class CatsApiRemoteDataSource(
    private val httpClient: HttpClient
) : CatsRemoteDataSource {
    private val baseUrl = "https://smite-handbook.onrender.com"

//    override suspend fun getGods(): List<GodApiResult> =
//        client.get("$baseUrl/gods").body()
//
//    override suspend fun getGodSkins(godId: Long): List<GodSkinApiResult> =
//        client.get("$baseUrl/godskins/${godId}").body()
//
//    override suspend fun getItems(): List<ItemApiResult> = client.get("$baseUrl/items").body()
//    override suspend fun getPatchVersion(): PatchVersionInfo = client.get("$baseUrl/patchinfo").body()
}