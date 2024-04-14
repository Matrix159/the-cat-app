package com.matrix159.feature.catbreeds.screens.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matrix159.feature.catbreeds.screens.catlist.CatListScreen
import com.matrix159.thecatapp.core.domain.model.Breed

fun NavGraphBuilder.catListRoute(
  catBreedSelected: (Breed) -> Unit,
) {
  composable(CatBreedNavigationRoutes.CatListScreen.route) {
    CatListScreen(
      catBreedSelected = catBreedSelected,
      modifier = Modifier.fillMaxSize()
    )
  }
}