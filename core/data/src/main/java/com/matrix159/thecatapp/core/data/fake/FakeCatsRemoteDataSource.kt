package com.matrix159.thecatapp.core.data.fake

import com.matrix159.thecatapp.core.data.CatsRemoteDataSource
import com.matrix159.thecatapp.core.data.model.ApiBreed
import com.matrix159.thecatapp.core.data.model.ApiImage

class FakeCatsRemoteDataSource: CatsRemoteDataSource {
  val breeds = listOf(
    ApiBreed(
      id = "1",
      name = "Abyssinian",
      image = ApiImage(
        id = "1",
        url = "https://example.com/image.jpg",
        width = 1600,
        height = 1200
      )
    ),
    ApiBreed(
      id = "2",
      name = "Aegean",
      image = ApiImage(
        id = "1",
        url = "https://example.com/image.jpg",
        width = 1200,
        height = 1000
      )
    ),
    ApiBreed(
      id = "3",
      name = "American Bobtail",
      image = ApiImage(
        id = "1",
        url = "https://example.com/image.jpg",
        width = 800,
        height = 600
      )
    ),
  )

  var throwError = false

  override suspend fun getBreeds(): List<ApiBreed> {
    if (throwError) {
      throw Exception("Fake error")
    }
    return breeds
  }
}