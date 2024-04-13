package com.matrix159.thecatapp.core.domain.repository

import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.Result

interface CatsRepository {
  /**
   * Get a list of cat breeds.
   * @return Result<List<Breed>> Result wrapped list of cat breeds.
   */
  suspend fun getBreeds(): Result<List<Breed>>

  /**
   * Get a cat breed by its id.
   * @param breedId The id of the cat breed.
   * @return Result<Breed> Result wrapped cat breed details.
   */
  suspend fun getBreedById(breedId: String): Result<Breed>

}