package com.matrix159.thecatapp.ui.navigation


interface Route {
  val route: String
}

sealed interface NavigationRoutes : Route {

  data object CatListScreen : NavigationRoutes {
    override val route: String = "cat_list"
  }

  data object CatDetailsScreen : NavigationRoutes {
    const val CAT_ID = "catId"
    override val route =
      "cat_details/{$CAT_ID}"

    fun generatePath(catId: Int) = "cat_details/${catId}"
  }
}
