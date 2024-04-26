package com.matrix159.feature.catbreeds.screens.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.matrix159.feature.catbreeds.screens.catdetails.CatDetailsScreen
import java.net.URLDecoder
import java.net.URLEncoder

internal const val BREED_ID_ARG = "breedId"
internal const val CAT_DETAILS_ROUTE_BASE = "cat_details_route"
const val CAT_DETAILS_ROUTE = "${CAT_DETAILS_ROUTE_BASE}/{$BREED_ID_ARG}"

internal class CatDetailsArgs(val breedId: String) {
  constructor(savedStateHandle: SavedStateHandle) : this(
    breedId = checkNotNull(savedStateHandle.get<String>(BREED_ID_ARG))
  )
}

fun NavController.navigateToCatDetails(breedId: String, navOptions: NavOptions? = null) {
  navigate("${CAT_DETAILS_ROUTE_BASE}/${breedId}", navOptions)
}

fun NavGraphBuilder.catDetailsRoute(
  showTwoPane: Boolean = false,
  navController: NavController
) {
  composable(
    CAT_DETAILS_ROUTE,
    arguments = listOf(
      navArgument(BREED_ID_ARG) {
        type = NavType.StringType
      })
  ) {
    CatDetailsScreen(
      showTwoPane = showTwoPane,
      navigateBack = navController::popBackStack,
      modifier = Modifier.fillMaxSize()
    )
  }
}