package com.matrix159.thecatapp.ui.screens.catlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.matrix159.thecatapp.R
import com.matrix159.thecatapp.core.domain.model.Breed

@Composable
fun CatListScreen(
  viewModel: CatListViewModel = hiltViewModel(),
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    when (val state = viewModel.uiState) {
      is CatListUiState.Success -> {
        LazyColumn {
          items(state.breeds) { breed ->
            CatCard(
              breed = breed,
              modifier = Modifier.fillMaxWidth()
            )
          }
        }
      }
      is CatListUiState.Error -> {
        Text("Error")
      }
      is CatListUiState.Loading -> {
        Text("Loading")
      }
    }
  }
}

@Composable
private fun CatCard(
  breed: Breed,
  modifier: Modifier = Modifier
) {
  Card(modifier = modifier){
    AsyncImage(
      model = breed.image?.url,
      contentDescription = stringResource(R.string.image_of_cat_breed, breed.name),
    )
    Text(text = breed.name)
  }
}