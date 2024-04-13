package com.matrix159.thecatapp.ui.screens.catlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

@Composable
fun CatListScreen(
  modifier: Modifier = Modifier,
  viewModel: CatListViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  Column(modifier = modifier) {
    when (val state = uiState) {
      is CatListUiState.Success -> {
        CatListScreen(
          state = state,
          modifier = Modifier.fillMaxWidth()
        )
      }

      is CatListUiState.Error -> {
        Text(stringResource(R.string.error))
      }

      is CatListUiState.Loading -> {
        Text(stringResource(R.string.loading))
      }
    }
  }
}

@Composable
private fun CatListScreen(
  state: CatListUiState.Success,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.s_padding)),
    modifier = modifier
  ) {
    items(state.breeds) { breed ->
      CatCard(
        breed = breed,
        modifier = Modifier
          .fillMaxWidth()
          .padding(dimensionResource(R.dimen.s_padding))
      )
    }
  }
}

@Composable
private fun CatCard(
  breed: Breed,
  modifier: Modifier = Modifier
) {
  Card(modifier = modifier) {
    Column {
      Text(
        text = breed.name,
        modifier = Modifier.padding(dimensionResource(R.dimen.m_padding))
      )
      AsyncImage(
        model = breed.image?.url,
        alignment = Alignment.Center,
        placeholder = painterResource(id = R.drawable.error_fallback),
        error = painterResource(id = R.drawable.error_fallback),
        contentDescription = stringResource(R.string.image_of_cat_breed, breed.name),
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .height(160.dp)
          .fillMaxWidth()
          .clip(shape = MaterialTheme.shapes.medium)
      )
    }
  }
}

@Preview
@Composable
private fun CatListScreenPreview() {
  CatAppTheme {
    CatListScreen(
      state = CatListUiState.Success(
        breeds = listOf(
          Breed(
            id = "1",
            name = "Abyssinian",
            image = null
          ),
          Breed(
            id = "2",
            name = "Aegean",
            image = null
          ),
          Breed(
            id = "3",
            name = "American Bobtail",
            image = null
          ),
        )
      )
    )
  }
}