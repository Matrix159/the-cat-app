package com.matrix159.thecatapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matrix159.thecatapp.core.domain.model.Breed
import com.matrix159.thecatapp.ui.screens.catlist.CatListScreen

fun NavGraphBuilder.catListRoute(
  catBreedSelected: (Breed) -> Unit,
) {
  composable(NavigationRoutes.CatListScreen.route) {
    CatListScreen(
      catBreedSelected = catBreedSelected,
      modifier = Modifier.fillMaxSize()
    )
  }
}