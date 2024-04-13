package com.matrix159.thecatapp

import com.matrix159.thecatapp.core.data.fake.FakeCatsRepository
import com.matrix159.thecatapp.ui.screens.catlist.CatListUiState
import com.matrix159.thecatapp.ui.screens.catlist.CatListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test

 class CatListViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val fakeCatsRepository = FakeCatsRepository()

  @Test
  fun `UI State is Loading by default`() = runTest {
    val viewModel = CatListViewModel(fakeCatsRepository)
//    Dispatchers.setMain(mainDispatcherRule.testDispatcher)
    assert(viewModel.uiState == CatListUiState.Loading)
  }
}