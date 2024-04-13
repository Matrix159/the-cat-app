package com.matrix159.thecatapp.core.data.fake

import com.matrix159.thecatapp.core.domain.Result
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.model.Image
import com.matrix159.thecatapp.core.domain.repository.CatsRepository

class FakeCatsRepository : CatsRepository {
  var shouldReturnError = false
  val breeds = listOf(
    Breed(
      id = "1",
      image = Image("1", 1200, 1000, url = "https://example.com/image.jpg"),
      name = "Short hair",
      description = "Short hair description"
    ),
    Breed(
      id = "2",
      image = Image("1", 1600, 1400, url = "https://example.com/image.jpg"),
      name = "Long hair",
      description = "Long hair description"
    ),
  )

  override suspend fun getBreeds(): Result<List<Breed>> {
    return if (shouldReturnError) {
      Result.Error(Exception("Error fetching breeds"))
    } else {
      Result.Success(
        breeds
      )
    }
  }

  override suspend fun getBreedById(breedId: String): Result<Breed> {
    return if (shouldReturnError) {
      Result.Error(Exception("Error fetching breed by id"))
    } else {
      Result.Success(
        breeds.first { it.id == breedId }
      )
    }
  }
}