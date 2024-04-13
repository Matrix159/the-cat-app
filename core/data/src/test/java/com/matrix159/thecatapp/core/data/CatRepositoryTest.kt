package com.matrix159.thecatapp.core.data

import com.matrix159.thecatapp.core.data.fake.FakeCatsRemoteDataSource
import com.matrix159.thecatapp.core.domain.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CatRepositoryTest {

  private val fakeCatsRemoteDataSource = FakeCatsRemoteDataSource()
  private val catRepository =
    DefaultCatsRepository(fakeCatsRemoteDataSource, UnconfinedTestDispatcher())

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