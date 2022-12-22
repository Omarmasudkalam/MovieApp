package com.omk.MainScreen.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.omk.MainScreen.ui.dashboard.DashboardSections
import com.omk.MainScreen.ui.dashboard.home.Home
import com.omk.MainScreen.ui.moviedetail.MovieDetail

import com.omk.MainScreen.ui.navigation.MainDestinations.MOVIE_ID_KEY
import kotlinx.coroutines.CoroutineScope


object MainDestinations {
  const val DASHBOARD_ROUTE = "dashboard"
  const val MOVIE_DETAIL_ROUTE = "movieDetail"
  const val MOVIE_ID_KEY = "movieId"
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun NavGraph(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  startDestination: String = MainDestinations.DASHBOARD_ROUTE,
  bottomSheetScaffoldState: BottomSheetScaffoldState,
  bottomSheetCoroutineScope: CoroutineScope,
  homeScreenScrollState: LazyListState,
  mainNavActions: MainActions
) {
  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {
    navigation(
      route = MainDestinations.DASHBOARD_ROUTE,
      startDestination = DashboardSections.HOME.route
    ) {
      composable(DashboardSections.HOME.route) {
        Home(
          bottomSheetScaffoldState = bottomSheetScaffoldState,
          modifier = modifier,
          coroutineScope = bottomSheetCoroutineScope,
          scrollState = homeScreenScrollState
        )
      }
    }
    composable(
      route = "${MainDestinations.MOVIE_DETAIL_ROUTE}/{$MOVIE_ID_KEY}",
      arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.LongType })
    ) { from: NavBackStackEntry ->

      BackHandler {
        mainNavActions.upPress(from)
      }


      MovieDetail(
        upPress = {
          mainNavActions.upPress(from)
        }
      )
    }
  }
}


class MainActions(
  navController: NavHostController,
  updateAppBarVisibility: (Boolean) -> Unit
) {
  val openMovieDetails = { movieId: Long ->
    updateAppBarVisibility(false)
    navController.navigate("${MainDestinations.MOVIE_DETAIL_ROUTE}/$movieId") {
      popUpTo(navController.graph.startDestinationId)
      // Avoid multiple copies of the same destination when re-selecting the same item
      launchSingleTop = true
    }
  }
  val upPress: (rom: NavBackStackEntry) -> Unit = { from: NavBackStackEntry ->
    // In order to discard duplicated navigation events, we check the Lifecycle
    if (from.lifecycleIsResumed()) {
      updateAppBarVisibility(true)
      navController.navigateUp()
    }
  }
}

fun NavBackStackEntry.lifecycleIsResumed() = this.lifecycle.currentState == Lifecycle.State.RESUMED
