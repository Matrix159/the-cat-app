package com.matrix159.feature.catbreeds.screens.catlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.core.ui.R as CommonR
import com.matrix159.thecatapp.core.ui.theme.CatAppPreviews
import com.matrix159.thecatapp.core.ui.theme.CatAppTheme
import com.matrix159.thecatapp.feature.catbreeds.R

/**
 * A Material3 card displaying a cat breed.
 * @param breed The breed to display.
 * @param modifier The modifier to apply to the card.
 * @param onClick The action to perform when the card is clicked.
 */
@Composable
fun CatCard(
  breed: Breed,
  modifier: Modifier = Modifier,
  onClick: (Breed) -> Unit = {},
) {
  Card(
    onClick = { onClick(breed) },
    modifier = modifier
  ) {
    Column {
      Text(
        text = breed.name,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(dimensionResource(CommonR.dimen.m_padding))
      )
      AsyncImage(
        model = breed.image?.url,
        alignment = Alignment.Center,
        placeholder = painterResource(id = CommonR.drawable.error_fallback),
        error = painterResource(id = CommonR.drawable.error_fallback),
        contentDescription = stringResource(R.string.image_of_cat_breed, breed.name),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
          .fillMaxWidth()
          .clip(shape = MaterialTheme.shapes.medium)
      )
    }
  }
}

@CatAppPreviews
@Composable
fun CatCardPreview() {
  CatAppTheme {
    CatCard(
      breed = Breed(
        id = "1",
        name = "Abyssinian",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        image = null,
        childFriendly = 5,
        dogFriendly = 5,
        energyLevel = 5
      )
    )
  }
}