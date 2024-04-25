package com.matrix159.thecatapp.ui

import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matrix159.feature.catbreeds.screens.navigation.CatBreedNavigationRoutes
import com.matrix159.feature.catbreeds.screens.navigation.catDetailsRoute
import com.matrix159.feature.catbreeds.screens.navigation.catListRoute

@Composable
fun CatApp(
  windowSizeClass: WindowSizeClass,
  modifier: Modifier = Modifier
) {
  val navController = rememberNavController()

  Surface(modifier = modifier) {
    NavHost(
      navController = navController,
      startDestination = CatBreedNavigationRoutes.CatListScreen.route,
    ) {
      val isCompactHeightClass = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact
      catListRoute(
        showAsGrid = isCompactHeightClass,
        catBreedSelected = {
          navController.navigate(CatBreedNavigationRoutes.CatDetailsScreen.generatePath(it.id))
        }
      )
      catDetailsRoute(
        showTwoPane = isCompactHeightClass,
        navController = navController
      )
    }
  }
}
