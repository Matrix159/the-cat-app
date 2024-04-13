package com.matrix159.thecatapp.ui.screens.catlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix159.thecatapp.core.domain.Result
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
  private val catsRepository: CatsRepository
) : ViewModel() {

  private val breedsFlow: Flow<Result<List<Breed>>> = flow {
    emit(catsRepository.getBreeds())
  }

  val uiState = breedsFlow.map {
    when (it) {
      is Result.Success -> CatListUiState.Success(it.data)
      else -> CatListUiState.Error
    }
  }.stateIn(
    viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = CatListUiState.Loading
  )
}

sealed interface CatListUiState {
  data class Success(
    val breeds: List<Breed>
  ) : CatListUiState

  data object Loading : CatListUiState
  data object Error : CatListUiState
}