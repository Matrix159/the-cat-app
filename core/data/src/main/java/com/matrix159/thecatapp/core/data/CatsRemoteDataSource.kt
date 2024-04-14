package com.matrix159.thecatapp.core.data

import com.matrix159.thecatapp.core.data.model.ApiBreed

/**
 * Remote data source for cat data.
 */
internal interface CatsRemoteDataSource {
  /**
   * Get a list of cat breeds.
   * @return List<ApiBreed> List of cat breeds from the remote data source.
   */
  suspend fun getBreeds(): List<ApiBreed>
}