package com.matrix159.thecatapp.ui

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matrix159.thecatapp.ui.navigation.NavigationRoutes
import com.matrix159.thecatapp.ui.navigation.catDetailsRoute
import com.matrix159.thecatapp.ui.navigation.catListRoute

@Composable
fun CatApp(
  modifier: Modifier = Modifier
) {
  val navController = rememberNavController()

  Surface(modifier = modifier) {
    NavHost(
      navController = navController,
      startDestination = NavigationRoutes.CatListScreen.route,
    ) {
      catListRoute(navController = navController)
      catDetailsRoute(navController = navController)
    }
  }
}
