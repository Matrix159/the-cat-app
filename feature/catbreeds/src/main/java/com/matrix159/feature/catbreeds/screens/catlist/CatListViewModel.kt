package com.matrix159.feature.catbreeds.screens.catlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix159.thecatapp.core.domain.Result
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CatListViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val catsRepository: CatsRepository
) : ViewModel() {

  private val searchInputKey = "searchInput"
  private var searchInput = savedStateHandle.getStateFlow(searchInputKey, "")

  private val refreshing = MutableStateFlow(false)
  // Maybe consider other approaches of doing this, but this allows the refreshing state to remain
  // in the UI state and to only fetch the initial breeds upon UI subscription.
  private val backingBreedsFlow = MutableSharedFlow<Result<List<Breed>>>(replay = 1)
  private val breedsFlow = backingBreedsFlow.onSubscription {
    emit(catsRepository.getBreeds())
  }

  val uiState = combine(
    refreshing,
    searchInput,
    breedsFlow
  ) { refreshing, searchInput, breedsResult ->
    when (breedsResult) {
      is Result.Success -> {
        val filteredBreeds = if (searchInput.isNotEmpty()) {
          breedsResult.data.filter {
            it.name.contains(searchInput, ignoreCase = true)
          }
        } else {
          breedsResult.data
        }

        CatListUiState.Success(
          searchInput = searchInput,
          breeds = filteredBreeds,
          refreshing = refreshing
        )
      }

      else -> CatListUiState.Error
    }
  }.stateIn(
    viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = CatListUiState.Loading
  )

  fun updateSearchInput(input: String) {
    savedStateHandle[searchInputKey] = input
  }

  fun refreshBreeds() {
    viewModelScope.launch {
      refreshing.value = true
      backingBreedsFlow.emit(catsRepository.getBreeds())
      refreshing.value = false
    }
  }
}

sealed interface CatListUiState {
  data class Success(
    val searchInput: String,
    val breeds: List<Breed>,
    val refreshing: Boolean
  ) : CatListUiState

  data object Loading : CatListUiState
  data object Error : CatListUiState
}