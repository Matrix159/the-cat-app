package com.matrix159.feature.catbreeds

import androidx.lifecycle.SavedStateHandle
import com.matrix159.feature.catbreeds.screens.catlist.CatListUiState
import com.matrix159.feature.catbreeds.screens.catlist.CatListViewModel
import com.matrix159.thecatapp.core.data.fake.FakeCatsRepository
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.model.Image
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CatListViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val savedStateHandle = SavedStateHandle()
  private val fakeCatsRepository = FakeCatsRepository()
  private lateinit var catListViewModel: CatListViewModel

  @Before
  fun setup() {
    catListViewModel = CatListViewModel(savedStateHandle, fakeCatsRepository)
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

  @Test
  fun `UI State starts with empty search input`() = runTest {
    val collectJob = launch(UnconfinedTestDispatcher()) { catListViewModel.uiState.collect() }
    val uiState = catListViewModel.uiState.value
    assert(uiState is CatListUiState.Success)
    assert((uiState as CatListUiState.Success).searchInput.isEmpty())
    collectJob.cancel()
  }

  @Test
  fun `UI State has filled out search input`() = runTest {
    val searchInput = "search"
    catListViewModel.updateSearchInput(searchInput)
    val collectJob = launch(UnconfinedTestDispatcher()) { catListViewModel.uiState.collect() }
    val uiState = catListViewModel.uiState.value
    assert(uiState is CatListUiState.Success)
    assert((uiState as CatListUiState.Success).searchInput == searchInput)
    collectJob.cancel()
  }

  @Test
  fun `Search input filters down available breeds`() = runTest {
    val searchInput = "search"
    catListViewModel.updateSearchInput(searchInput)
    val collectJob = launch(UnconfinedTestDispatcher()) { catListViewModel.uiState.collect() }
    val uiState = catListViewModel.uiState.value
    assert(uiState is CatListUiState.Success)
    assert((uiState as CatListUiState.Success).breeds == fakeCatsRepository.breeds.filter {
      it.name.contains(
        searchInput,
        ignoreCase = true
      )
    })
    collectJob.cancel()
  }

  @Test
  fun `List has different data after refreshing`() = runTest {
    val collectJob = launch(UnconfinedTestDispatcher()) { catListViewModel.uiState.collect() }
    val uiState = catListViewModel.uiState.value
    assert(uiState is CatListUiState.Success)
    val initialBreeds = (uiState as CatListUiState.Success).breeds
    fakeCatsRepository.addBreed(
      Breed(
        id = "3",
        childFriendly = 5,
        description = "some description",
        dogFriendly = 5,
        energyLevel = 5,
        image = Image("3", 1200, 1000, "https://example.com/image.jpg"),
        name = "Another cat breed breed"
      )
    )
    catListViewModel.setRefreshing(true)
    val refreshedUiState = catListViewModel.uiState.value
    assert(refreshedUiState is CatListUiState.Success)
    val refreshedBreeds = (refreshedUiState as CatListUiState.Success).breeds
    assert(initialBreeds != refreshedBreeds)
    collectJob.cancel()
  }

}