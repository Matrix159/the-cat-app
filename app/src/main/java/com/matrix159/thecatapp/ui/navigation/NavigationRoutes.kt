package com.matrix159.thecatapp.ui.navigation


interface Route {
  val route: String
}

sealed interface NavigationRoutes : Route {

  data object CatListScreen : NavigationRoutes {
    override val route: String = "cat_list"
  }

  data object CatDetailsScreen : NavigationRoutes {
    const val BREED_ID = "breedId"
    override val route =
      "cat_details/{$BREED_ID}"

    fun generatePath(breedId: String) = "cat_details/${breedId}"
  }
}
