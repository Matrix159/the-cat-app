package com.matrix159.thecatapp.ui.screens.catdetails

import androidx.lifecycle.ViewModel
import com.matrix159.thecatapp.core.domain.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatDetailsViewModel @Inject constructor(
  catsRepository: CatsRepository
): ViewModel() {
}