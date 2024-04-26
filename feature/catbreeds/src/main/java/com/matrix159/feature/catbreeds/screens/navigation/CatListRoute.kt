package com.matrix159.feature.catbreeds.screens.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.matrix159.feature.catbreeds.screens.catlist.CatListScreen
import com.matrix159.thecatapp.core.domain.model.Breed

const val CAT_LIST_ROUTE = "cat_list_route"

fun NavController.navigateToCatList(navOptions: NavOptions? = null) {
  navigate(CAT_LIST_ROUTE, navOptions)
}

fun NavGraphBuilder.catListRoute(
  showAsGrid: Boolean = false,
  catBreedSelected: (Breed) -> Unit,
) {
  composable(CAT_LIST_ROUTE) {
    CatListScreen(
      showAsGrid = showAsGrid,
      catBreedSelected = catBreedSelected,
      modifier = Modifier.fillMaxSize()
    )
  }
}