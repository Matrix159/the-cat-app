package com.matrix159.thecatapp.core.domain.repository

import com.matrix159.thecatapp.core.domain.model.Breed

interface CatsRepository {
  suspend fun getBreeds(): List<Breed>
}