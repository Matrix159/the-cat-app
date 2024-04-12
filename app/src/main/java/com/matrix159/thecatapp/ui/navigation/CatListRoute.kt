package com.matrix159.thecatapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matrix159.thecatapp.ui.screens.catlist.CatListScreen

fun NavGraphBuilder.catListRoute(
  navController: NavController,
) {
  composable(NavigationRoutes.CatListScreen.route) {
    CatListScreen(
      modifier = Modifier.fillMaxSize()
    )
  }
}