package com.matrix159.thecatapp.core.data

import com.matrix159.thecatapp.core.data.model.ApiBreed

internal interface CatsRemoteDataSource {
  suspend fun getBreeds(): List<ApiBreed>
}