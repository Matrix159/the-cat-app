package com.matrix159.thecatapp.ui.screens.catlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.matrix159.thecatapp.R
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.ui.theme.CatAppTheme
import com.matrix159.thecatapp.core.ui.theme.composable.LoadingIndicator

@Composable
fun CatListScreen(
  catBreedSelected: (Breed) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: CatListViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = modifier.padding(dimensionResource(R.dimen.m_padding))
    ) {
      when (val state = uiState) {
        is CatListUiState.Success -> {
          CatListScreen(
            state = state,
            catBreedSelected = catBreedSelected,
            modifier = Modifier.fillMaxWidth()
          )
        }

        is CatListUiState.Error -> {
          Text(stringResource(R.string.error))
        }

        is CatListUiState.Loading -> {
          LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
      }
    }
}

@Composable
private fun CatListScreen(
  state: CatListUiState.Success,
  catBreedSelected: (Breed) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.m_padding)),
    modifier = modifier
  ) {
    Text(
      text = stringResource(R.string.cat_breeds),
      style = MaterialTheme.typography.headlineLarge,
    )
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.s_padding)),
    ) {
      items(state.breeds) { breed ->
        CatCard(
          breed = breed,
          onClick = catBreedSelected,
          modifier = Modifier.fillMaxWidth()
        )
      }
    }
  }

}

@Composable
private fun CatCard(
  breed: Breed,
  onClick: (Breed) -> Unit = {},
  modifier: Modifier = Modifier
) {
  Card(
    onClick = { onClick(breed) },
    modifier = modifier
  ) {
    Column {
      Text(
        text = breed.name,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(dimensionResource(R.dimen.m_padding))
      )
      AsyncImage(
        model = breed.image?.url,
        alignment = Alignment.Center,
        placeholder = painterResource(id = R.drawable.error_fallback),
        error = painterResource(id = R.drawable.error_fallback),
        contentDescription = stringResource(R.string.image_of_cat_breed, breed.name),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
          .fillMaxWidth()
          .clip(shape = MaterialTheme.shapes.medium)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun CatListScreenPreview() {
  CatAppTheme {
    CatListScreen(
      state = CatListUiState.Success(
        breeds = listOf(
          Breed(
            id = "1",
            name = "Abyssinian",
            description = "The Abyssinian is easy to care for, and a joy to have in your home.",
            image = null
          ),
          Breed(
            id = "2",
            name = "Aegean",
            description = "The Aegean is a natural breed of domestic cat originating from the Cycladic Islands of Greece.",
            image = null
          ),
          Breed(
            id = "3",
            name = "American Bobtail",
            description = "American Bobtails are loving and incredibly intelligent cats known for their wild appearance.",
            image = null
          ),
        )
      ),
      catBreedSelected = {},
    )
  }
}