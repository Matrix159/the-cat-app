package com.matrix159.thecatapp

import androidx.lifecycle.SavedStateHandle
import com.matrix159.thecatapp.core.data.fake.FakeCatsRepository
import com.matrix159.thecatapp.ui.navigation.NavigationRoutes
import com.matrix159.thecatapp.ui.screens.catdetails.CatDetailsUiState
import com.matrix159.thecatapp.ui.screens.catdetails.CatDetailsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import com.matrix159.thecatapp.core.domain.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
internal class CatDetailsViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val fakeCatsRepository = FakeCatsRepository()
  private val breedId = fakeCatsRepository.breeds.first().id
  private val savedStateHandle = SavedStateHandle(mapOf(NavigationRoutes.CatDetailsScreen.BREED_ID to breedId))
  private lateinit var viewModel: CatDetailsViewModel

  @Before
  fun setup() {
    viewModel = CatDetailsViewModel(savedStateHandle, fakeCatsRepository)
  }

  @Test
  fun `UI State is Loading by default`() = runTest {
    val actual = viewModel.uiState.value
    assertEquals(CatDetailsUiState.Loading, actual)
  }

  @Test
  fun `CatDetailsViewModel should emit Success state with expected breed details`() = runTest {
    val result = fakeCatsRepository.getBreedById(breedId)
    val expected = CatDetailsUiState.Success((result as Result.Success).data)
    val actual = viewModel.uiState.first()
    assertEquals(expected, actual)
  }

  @Test
  fun `CatDetailsViewModel should emit Error state when repository returns error`() = runTest {
    fakeCatsRepository.shouldReturnError = true
    val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
    val actual = viewModel.uiState.first()
    assertEquals(CatDetailsUiState.Error, actual)

    collectJob.cancel()
  }

}