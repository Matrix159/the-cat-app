package com.matrix159.thecatapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.matrix159.thecatapp.ui.screens.catdetails.CatDetailsScreen

fun NavGraphBuilder.catDetailsRoute(
  navController: NavController,
) {
  composable(
    NavigationRoutes.CatDetailsScreen.route,
    arguments = listOf(navArgument(NavigationRoutes.CatDetailsScreen.CAT_ID) {
      type = NavType.IntType
    })
  ) {
    CatDetailsScreen(
      modifier = Modifier.fillMaxSize()
    )
  }
}