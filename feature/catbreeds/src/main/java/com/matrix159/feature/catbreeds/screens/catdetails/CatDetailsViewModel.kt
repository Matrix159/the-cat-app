package com.matrix159.feature.catbreeds.screens.catdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix159.feature.catbreeds.screens.navigation.CatBreedNavigationRoutes
import com.matrix159.thecatapp.core.domain.Result
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.domain.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CatDetailsViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  catsRepository: CatsRepository
) : ViewModel() {

  private val breedId =
    checkNotNull(savedStateHandle.get<String>(CatBreedNavigationRoutes.CatDetailsScreen.BREED_ID))

  private val breedDetailsFlow: Flow<Result<Breed>> = flow {
    emit(catsRepository.getBreedById(breedId))
  }

  val uiState = breedDetailsFlow.map {
    when (it) {
      is Result.Success -> CatDetailsUiState.Success(it.data)
      else -> CatDetailsUiState.Error
    }
  }.stateIn(
    viewModelScope,
    SharingStarted.WhileSubscribed(5_000),
    CatDetailsUiState.Loading
  )
}


sealed interface CatDetailsUiState {
  data class Success(val breed: Breed) : CatDetailsUiState
  data object Loading : CatDetailsUiState
  data object Error : CatDetailsUiState
}