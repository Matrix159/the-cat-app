package com.matrix159.thecatapp.core.data.fake

import com.matrix159.thecatapp.core.domain.Result
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.model.Image
import com.matrix159.thecatapp.core.domain.repository.CatsRepository

class FakeCatsRepository : CatsRepository {
  val breeds = listOf(
    Breed("1", Image("1", 1200, 1000, url = "https://example.com/image.jpg"), "Short hair"),
    Breed("2", Image("2", 1600, 1200, url = "https://example.com/image.jpg"), "Long hair"),
  )

  override suspend fun getBreeds(): Result<List<Breed>> {
    return Result.Success(breeds)
  }
}