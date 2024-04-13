package com.matrix159.thecatapp.core

import com.matrix159.thecatapp.core.data.DefaultCatsRepository
import com.matrix159.thecatapp.core.data.fake.FakeCatsRemoteDataSource
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import com.matrix159.thecatapp.core.domain.Result

internal class CatRepositoryTest {

  private val fakeCatsRemoteDataSource = FakeCatsRemoteDataSource()
  private val catRepository = DefaultCatsRepository(fakeCatsRemoteDataSource)

  @Test
  fun `getBreeds returns expected breeds`() = runTest {
    var result = catRepository.getBreeds()
    assert(result is Result.Success)
    result = result as Result.Success
    assert(result.data == fakeCatsRemoteDataSource.breeds.map { it.toBreed() })
  }

  @Test
  fun `getBreeds returns error result`() = runTest {
    fakeCatsRemoteDataSource.throwError = true
    val result = catRepository.getBreeds()
    assert(result is Result.Error)
  }
}