package com.matrix159.thecatapp.core.domain.repository

import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.Result

interface CatsRepository {
  suspend fun getBreeds(): Result<List<Breed>>
}