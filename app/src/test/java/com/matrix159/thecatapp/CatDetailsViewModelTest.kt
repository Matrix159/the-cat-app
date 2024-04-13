package com.matrix159.thecatapp

import com.matrix159.thecatapp.core.data.fake.FakeCatsRepository
import com.matrix159.thecatapp.ui.screens.catdetails.CatDetailsViewModel
import org.junit.Before
import org.junit.Rule

internal class CatDetailsViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val fakeCatsRepository = FakeCatsRepository()
  private lateinit var viewModel: CatDetailsViewModel

  @Before
  fun setup() {
    viewModel = CatDetailsViewModel(fakeCatsRepository)
  }

}