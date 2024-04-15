package com.matrix159.feature.catbreeds.screens.catdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
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
import com.matrix159.thecatapp.core.ui.theme.CatAppPreviews
import com.matrix159.thecatapp.core.ui.theme.CatAppTheme
import com.matrix159.thecatapp.core.ui.theme.composable.ErrorIndicator
import com.matrix159.thecatapp.core.ui.theme.composable.LoadingIndicator
import com.matrix159.thecatapp.core.ui.theme.composable.debugPlaceholder
import com.matrix159.thecatapp.feature.catbreeds.R
import com.matrix159.thecatapp.core.ui.R as CommonR

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
          contentDescription = stringResource(CommonR.string.go_to_previous_screen)
        )
      }
    },
    modifier = modifier.padding(dimensionResource(id = CommonR.dimen.s_padding))
  ) { padding ->
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .padding(padding)
        .consumeWindowInsets(padding)
        .fillMaxWidth(),
    ) {
      when (val state = uiState) {
        is CatDetailsUiState.Success -> {
          CatDetailsScreen(
            state = state,
            modifier = modifier.fillMaxWidth()
          )
        }

        is CatDetailsUiState.Error -> {
          ErrorIndicator()
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
    Spacer(modifier = Modifier.height(dimensionResource(id = CommonR.dimen.s_padding)))
    BreedStats(breed = breed)
    Spacer(modifier = Modifier.height(dimensionResource(id = CommonR.dimen.s_padding)))
    AsyncImage(
      model = breed.image?.url,
      error = painterResource(id = CommonR.drawable.error_fallback),
      placeholder = debugPlaceholder(debugPreview = CommonR.drawable.error_fallback),
      contentDescription = stringResource(R.string.image_of_cat_breed, breed.name),
      contentScale = ContentScale.FillWidth,
      modifier = Modifier
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.small)
    )
  }
}

/**
 * Display the stats of the breed. Stats shown are child friendly, dog friendly, and energy levels.
 * @param breed The breed to display the stats for.
 * @param modifier The modifier for the stats.
 */
@Composable
private fun BreedStats(
  breed: Breed,
  modifier: Modifier = Modifier
) {
  Column(modifier) {
    StatRow(
      label = stringResource(R.string.child_friendly),
      value = breed.childFriendly
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = CommonR.dimen.s_padding)))
    StatRow(
      label = stringResource(R.string.dog_friendly),
      value = breed.dogFriendly
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = CommonR.dimen.s_padding)))
    StatRow(
      label = stringResource(R.string.energy_level),
      value = breed.energyLevel
    )
  }
}

/**
 * Display a row of stars to represent the value of the stat.
 * @param label The label of the stat.
 * @param value The value of the stat.
 * @param modifier The modifier for the row.
 */
@Composable
private fun StatRow(
  label: String,
  value: Int,
  modifier: Modifier = Modifier,
) {
  Column(modifier) {
    Text(
      text = label,
      style = MaterialTheme.typography.labelLarge
    )
    Row {
      for (i in 1..value) {
        Icon(
          imageVector = Icons.Default.Star,
          tint = MaterialTheme.colorScheme.primary,
          contentDescription = null
        )
      }
      for (i in value + 1..5) {
        Icon(
          imageVector = Icons.TwoTone.Star,
          tint = MaterialTheme.colorScheme.primary,
          contentDescription = null
        )
      }
    }
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
            image = null,
            childFriendly = 4,
            dogFriendly = 4,
            energyLevel = 5
          )
        ),
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}