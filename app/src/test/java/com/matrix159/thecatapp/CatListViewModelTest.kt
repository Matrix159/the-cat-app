package com.matrix159.thecatapp

import com.matrix159.thecatapp.core.data.fake.FakeCatsRepository
import com.matrix159.thecatapp.ui.screens.catlist.CatListUiState
import com.matrix159.thecatapp.ui.screens.catlist.CatListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CatListViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val fakeCatsRepository = FakeCatsRepository()
  private lateinit var catListViewModel: CatListViewModel

  @Before
  fun setup() {
    catListViewModel = CatListViewModel(fakeCatsRepository)
  }

  @Test
  fun `UI State is Loading by default`() = runTest {
    val uiState = catListViewModel.uiState.value
    assert(uiState is CatListUiState.Loading)
  }

  @Test
  fun `UI State is Success when repository returns breeds`() = runTest {
    val collectJob = launch(UnconfinedTestDispatcher()) { catListViewModel.uiState.collect() }
    val uiState = catListViewModel.uiState.value
    assert(uiState is CatListUiState.Success)
    collectJob.cancel()
  }

  @Test
  fun `UI State has correct breeds`() = runTest {
    val collectJob = launch(UnconfinedTestDispatcher()) { catListViewModel.uiState.collect() }
    val uiState = catListViewModel.uiState.value
    assert(uiState is CatListUiState.Success)
    assert((uiState as CatListUiState.Success).breeds == fakeCatsRepository.breeds)
    collectJob.cancel()
  }

  @Test
  fun `UI State is Error when repository returns error`() = runTest {
    fakeCatsRepository.shouldReturnError = true
    val collectJob = launch(UnconfinedTestDispatcher()) { catListViewModel.uiState.collect() }
    val uiState = catListViewModel.uiState.value
    assert(uiState is CatListUiState.Error)
    collectJob.cancel()
  }
}