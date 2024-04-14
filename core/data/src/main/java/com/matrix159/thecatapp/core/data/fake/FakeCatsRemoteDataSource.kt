package com.matrix159.thecatapp.core.data.fake

import com.matrix159.thecatapp.core.data.CatsRemoteDataSource
import com.matrix159.thecatapp.core.data.model.ApiBreed
import com.matrix159.thecatapp.core.data.model.ApiImage

/**
 * Fake implementation of [CatsRemoteDataSource] for testing purposes.
 */
class FakeCatsRemoteDataSource: CatsRemoteDataSource {
  internal val breeds = listOf(
    ApiBreed(
      id = "1",
      name = "Abyssinian",
      description = "The Abyssinian is easy to care for, and a joy to have in your home.",
      childFriendly = 5,
      dogFriendly = 5,
      energyLevel = 5,
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
      description = "The Aegean is a natural breed of domestic cat originating from the Cycladic Islands of Greece.",
      childFriendly = 5,
      dogFriendly = 5,
      energyLevel = 5,
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
      description = "American Bobtails are loving and incredibly intelligent cats known for their wild appearance.",
      childFriendly = 5,
      dogFriendly = 5,
      energyLevel = 5,
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