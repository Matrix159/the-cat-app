package com.matrix159.thecatapp.ui

import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matrix159.feature.catbreeds.screens.navigation.CAT_LIST_ROUTE
import com.matrix159.feature.catbreeds.screens.navigation.catDetailsRoute
import com.matrix159.feature.catbreeds.screens.navigation.catListRoute
import com.matrix159.feature.catbreeds.screens.navigation.navigateToCatDetails

@Composable
fun CatApp(
  windowSizeClass: WindowSizeClass,
  modifier: Modifier = Modifier
) {
  val navController = rememberNavController()

  Surface(modifier = modifier) {
    NavHost(
      navController = navController,
      startDestination = CAT_LIST_ROUTE,
    ) {
      val isCompactHeightClass = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact
      catListRoute(
        showAsGrid = isCompactHeightClass,
        catBreedSelected = { breed ->
          navController.navigateToCatDetails(breed.id)
        }
      )
      catDetailsRoute(
        showTwoPane = isCompactHeightClass,
        navController = navController
      )
    }
  }
}
