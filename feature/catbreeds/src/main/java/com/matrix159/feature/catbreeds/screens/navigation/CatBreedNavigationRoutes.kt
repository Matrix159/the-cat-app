package com.matrix159.feature.catbreeds.screens.navigation


interface Route {
  val route: String
}

sealed interface CatBreedNavigationRoutes : Route {

  data object CatListScreen : CatBreedNavigationRoutes {
    override val route: String = "cat_list"
  }

  data object CatDetailsScreen : CatBreedNavigationRoutes {
    const val BREED_ID = "breedId"
    override val route =
      "cat_details/{$BREED_ID}"

    fun generatePath(breedId: String) = "cat_details/${breedId}"
  }
}
