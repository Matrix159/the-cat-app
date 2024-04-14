package com.matrix159.feature.catbreeds.screens.catdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.ui.R
import com.matrix159.thecatapp.core.ui.theme.CatAppPreviews
import com.matrix159.thecatapp.core.ui.theme.CatAppTheme
import com.matrix159.thecatapp.core.ui.theme.composable.LoadingIndicator

@Composable
fun CatDetailsScreen(
  navigateBack: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: CatDetailsViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  Scaffold(
    topBar = {
      IconButton(onClick = navigateBack) {
        Icon(
          imageVector = Icons.AutoMirrored.Default.ArrowBack,
          contentDescription = stringResource(R.string.go_to_previous_screen)
        )
      }
    },
    modifier = modifier.padding(dimensionResource(id = R.dimen.s_padding))
  ) { padding ->
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .padding(padding)
        .consumeWindowInsets(padding),
    ) {
      when (val state = uiState) {
        is CatDetailsUiState.Success -> {
          CatDetailsScreen(
            state = state,
            modifier = modifier.fillMaxWidth()
          )
        }

        is CatDetailsUiState.Error -> {
          Text(stringResource(R.string.error))
        }

        is CatDetailsUiState.Loading -> {
          LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
      }
    }
  }
}

@Composable
private fun CatDetailsScreen(
  state: CatDetailsUiState.Success,
  modifier: Modifier = Modifier
) {
  val breed = state.breed
  Column(modifier = modifier.verticalScroll(rememberScrollState())) {
    Text(
      text = breed.name,
      style = MaterialTheme.typography.headlineLarge
    )
    Text(
      text = breed.description
    )
    AsyncImage(
      model = breed.image?.url,
      placeholder = painterResource(id = R.drawable.error_fallback),
      error = painterResource(id = R.drawable.error_fallback),
      contentDescription = stringResource(R.string.image_of_cat_breed, breed.name),
      contentScale = ContentScale.FillWidth,
      modifier = Modifier
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.small)
    )
  }
}

@CatAppPreviews
@Composable
private fun CatDetailsScreenPreview() {
  CatAppTheme {
    Surface {
      CatDetailsScreen(
        state = CatDetailsUiState.Success(
          breed = Breed(
            id = "1",
            name = "Abyssinian",
            description = "The Abyssinian is easy to care for, and a joy to have in your home.",
            image = null
          )
        ),
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}