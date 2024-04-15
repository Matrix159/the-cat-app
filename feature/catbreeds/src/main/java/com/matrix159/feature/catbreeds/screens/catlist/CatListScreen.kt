package com.matrix159.feature.catbreeds.screens.catlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.ui.theme.CatAppPreviews
import com.matrix159.thecatapp.core.ui.theme.CatAppTheme
import com.matrix159.thecatapp.core.ui.theme.composable.ErrorIndicator
import com.matrix159.thecatapp.core.ui.theme.composable.LoadingIndicator
import com.matrix159.thecatapp.feature.catbreeds.R
import com.matrix159.thecatapp.core.ui.R as CommonR

@Composable
fun CatListScreen(
  catBreedSelected: (Breed) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: CatListViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.padding(dimensionResource(CommonR.dimen.m_padding))
  ) {
    when (val state = uiState) {
      is CatListUiState.Success -> {
        CatListScreen(
          state = state,
          refreshing = viewModel.refreshing,
          searchInputUpdated = viewModel::updateSearchInput,
          catBreedSelected = catBreedSelected,
          refresh = viewModel::refresh,
          modifier = Modifier.fillMaxWidth()
        )
      }

      is CatListUiState.Error -> {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          ErrorIndicator()
          Button(onClick = viewModel::refresh) {
            Text(stringResource(R.string.retry))
          }
        }
      }

      is CatListUiState.Loading -> {
        LoadingIndicator(modifier = Modifier.fillMaxSize())
      }
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CatListScreen(
  state: CatListUiState.Success,
  refreshing: Boolean,
  searchInputUpdated: (String) -> Unit,
  catBreedSelected: (Breed) -> Unit,
  refresh: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(dimensionResource(CommonR.dimen.m_padding)),
    modifier = modifier
  ) {
    Text(
      text = stringResource(R.string.cat_breeds),
      style = MaterialTheme.typography.headlineLarge,
    )
    OutlinedTextField(
      value = state.searchInput,
      onValueChange = searchInputUpdated,
      label = { Text(stringResource(R.string.search_by_breed)) },
      keyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done
      ),
      modifier = Modifier.fillMaxWidth()
    )
    val pullRefreshState = rememberPullRefreshState(refreshing, refresh)

    Box(Modifier.pullRefresh(pullRefreshState)) {
      LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(CommonR.dimen.s_padding)),
      ) {
        items(state.breeds) { breed ->
          CatCard(
            breed = breed,
            onClick = catBreedSelected,
            modifier = Modifier.fillMaxWidth()
          )
        }
      }

      PullRefreshIndicator(
        refreshing = refreshing,
        state = pullRefreshState,
        modifier = Modifier.align(Alignment.Center)
      )
    }
  }

}

@CatAppPreviews
@Composable
private fun CatListScreenPreview() {
  CatAppTheme {
    Surface {
      CatListScreen(
        state = CatListUiState.Success(
          searchInput = "",
          breeds = listOf(
            Breed(
              id = "1",
              name = "Abyssinian",
              description = "The Abyssinian is easy to care for, and a joy to have in your home.",
              childFriendly = 5,
              dogFriendly = 5,
              energyLevel = 5,
              image = null
            ),
            Breed(
              id = "2",
              name = "Aegean",
              description = "The Aegean is a natural breed of domestic cat originating from the Cycladic Islands of Greece.",
              childFriendly = 5,
              dogFriendly = 5,
              energyLevel = 5,
              image = null
            ),
            Breed(
              id = "3",
              name = "American Bobtail",
              description = "American Bobtails are loving and incredibly intelligent cats known for their wild appearance.",
              childFriendly = 5,
              dogFriendly = 5,
              energyLevel = 5,
              image = null
            ),
          ),
        ),
        refreshing = false,
        searchInputUpdated = {},
        catBreedSelected = {},
        refresh = {},
      )
    }
  }
}