package com.matrix159.thecatapp.core.data

import com.matrix159.thecatapp.core.domain.Result
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.repository.CatsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class DefaultCatsRepository(
  private val catsRemoteDataSource: CatsRemoteDataSource,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CatsRepository {
  override suspend fun getBreeds(): Result<List<Breed>> = withContext(dispatcher) {
    return@withContext try {
      Result.Success(catsRemoteDataSource.getBreeds().map { it.toBreed() })
    } catch (e: Exception) {
      Result.Error(e)
    }
  }
}