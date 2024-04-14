package com.matrix159.feature.catbreeds.screens.catlist

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.matrix159.thecatapp.core.domain.Result
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class CatListViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  private val catsRepository: CatsRepository
) : ViewModel() {

  private var searchInput by savedStateHandle.saveable { mutableStateOf("") }
  private val breedsFlow: Flow<Result<List<Breed>>> = flow {
    emit(catsRepository.getBreeds())
  }

  val uiState = combine(
    snapshotFlow { searchInput },
    breedsFlow
  ) { searchInput, breedsResult ->
    when (breedsResult) {
      is Result.Success -> {
        val filteredBreeds = if (searchInput.isNotEmpty()) {
          breedsResult.data.filter {
            it.name.contains(searchInput, ignoreCase = true)
          }
        } else {
          breedsResult.data
        }
        CatListUiState.Success(searchInput, filteredBreeds)
      }
      else -> CatListUiState.Error
    }
  }.stateIn(
    viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = CatListUiState.Loading
  )

  fun updateSearchInput(input: String) {
    searchInput = input
  }
}

sealed interface CatListUiState {
  data class Success(
    val searchInput: String,
    val breeds: List<Breed>
  ) : CatListUiState

  data object Loading : CatListUiState
  data object Error : CatListUiState
}