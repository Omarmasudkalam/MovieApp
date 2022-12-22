package com.omk.MainScreen.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.omk.MainScreen.ui.components.MovieAppScaffold
import com.omk.MainScreen.ui.components.TopAppBar
import com.omk.MainScreen.ui.navigation.NavGraph
import com.omk.MainScreen.ui.navigation.MainActions
import com.omk.MainScreen.ui.theme.ChillFlixTheme
import com.omk.MainScreen.ui.viewmodel.ProvideMultiViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.omk.MainScreen.ui.dashboard.BottomNavigationBar
import com.omk.MainScreen.ui.dashboard.DashboardSections
import com.omk.MainScreen.ui.dashboard.home.component.BottomSheetContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun App() {
  ProvideWindowInsets {
    ChillFlixTheme {
      ProvideMultiViewModel {

        val (shouldShowAppBar, updateAppBarVisibility) = remember { mutableStateOf(true) }
        val navController = rememberNavController()
        val tabs = remember { DashboardSections.values() }
        val bottomSheetCoroutineScope = rememberCoroutineScope()
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
          bottomSheetState = BottomSheetState(Collapsed)
        )
        val homeScreenScrollState = rememberLazyListState()
        val isScrolledDown = remember {
          derivedStateOf {
            homeScreenScrollState.firstVisibleItemScrollOffset > 0
          }
        }
        val mainNavActions = remember(navController) {
          MainActions(navController, updateAppBarVisibility)
        }

        BottomSheetScaffold(
          scaffoldState = bottomSheetScaffoldState,
          sheetContent = {
            BottomSheetContent(
              onMovieClick = { movieId: Long ->
                closeBottomSheet(bottomSheetCoroutineScope, bottomSheetScaffoldState)
                mainNavActions.openMovieDetails(movieId)
              },
              onBottomSheetClosePressed = {
                closeBottomSheet(bottomSheetCoroutineScope, bottomSheetScaffoldState)
              }
            )
          },
          sheetPeekHeight = 0.dp
        ) {
          MovieAppScaffold(
            bottomBar = { BottomNavigationBar(navController = navController, tabs = tabs)
            }
          ) { innerPaddingModifier ->

            NavGraph(
              navController = navController,
              modifier = Modifier.padding(innerPaddingModifier),
              bottomSheetScaffoldState = bottomSheetScaffoldState,
              bottomSheetCoroutineScope = bottomSheetCoroutineScope,
              homeScreenScrollState = homeScreenScrollState,
              mainNavActions = mainNavActions
            )
            if (shouldShowAppBar) {
              TopAppBar(isScrolledDown = isScrolledDown.value)
            }
          }
        }
      }
    }
  }
}

@ExperimentalMaterialApi
private fun closeBottomSheet(
  bottomSheetCoroutineScope: CoroutineScope,
  bottomSheetScaffoldState: BottomSheetScaffoldState
) {
  bottomSheetCoroutineScope.launch {
    bottomSheetScaffoldState.bottomSheetState.collapse()
  }
}
