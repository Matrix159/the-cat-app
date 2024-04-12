package com.matrix159.thecatapp.ui.screens.catlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
  private val catsRepository: CatsRepository
) : ViewModel() {

  var uiState by mutableStateOf<CatListUiState>(CatListUiState.Loading)
    private set

  init {
    viewModelScope.launch {
      uiState = try {
        CatListUiState.Success(catsRepository.getBreeds())
      } catch (e: Exception) {
        CatListUiState.Error
      }
    }
  }
}

sealed interface CatListUiState {
  data class Success(
    val breeds: List<Breed>
  ) : CatListUiState

  data object Loading : CatListUiState
  data object Error : CatListUiState
}