package com.omk.MainScreen.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.omk.MainScreen.ui.dashboard.home.viewmodel.MovieByIdViewModel
import com.omk.MainScreen.ui.dashboard.home.viewmodel.NowPlayingMoviesViewModel
import com.omk.MainScreen.ui.dashboard.home.viewmodel.PopularMoviesViewModel
import com.omk.MainScreen.ui.dashboard.home.viewmodel.TopRatedMoviesViewModel

object ViewModelProvider {
  val topRatedMoviesViewModel: TopRatedMoviesViewModel
    @Composable
    get() = LocalTopRatedMoviesViewModel.current

  val nowPlayingMoviesViewModel: NowPlayingMoviesViewModel
    @Composable
    get() = LocalNowPlayingMoviesViewModel.current

  val popularMoviesViewModel: PopularMoviesViewModel
    @Composable
    get() = LocalPopularMoviesViewModel.current

  val movieByIdViewModel: MovieByIdViewModel
    @Composable
    get() = LocalMovieByIdViewModel.current

  val selectedMovieViewModel: SelectedMovieViewModel
    @Composable
    get() = LocalSelectedMovieViewModel.current

}

@Composable
fun ProvideMultiViewModel(content: @Composable () -> Unit) {
  val topRatedMoviesVM: TopRatedMoviesViewModel = viewModel()
  val nowPlayingMoviesVM: NowPlayingMoviesViewModel = viewModel()
  val popularMoviesVM: PopularMoviesViewModel = viewModel()
  val movieByIdVM: MovieByIdViewModel = viewModel()
  val selectedMovieVM: SelectedMovieViewModel = viewModel()

  CompositionLocalProvider(
    LocalTopRatedMoviesViewModel provides topRatedMoviesVM,
  ) {
    CompositionLocalProvider(
      LocalNowPlayingMoviesViewModel provides nowPlayingMoviesVM,
    ) {
      CompositionLocalProvider(
        LocalPopularMoviesViewModel provides popularMoviesVM
      ) {
        CompositionLocalProvider(
          LocalMovieByIdViewModel provides movieByIdVM
        ) {
          CompositionLocalProvider(
            LocalSelectedMovieViewModel provides selectedMovieVM
          ) {
                content()
              }
            }
          }
        }
    }
  }

private val LocalTopRatedMoviesViewModel = staticCompositionLocalOf<TopRatedMoviesViewModel> {
  error("No TopRatedMoviesViewModel provided")
}

private val LocalNowPlayingMoviesViewModel = staticCompositionLocalOf<NowPlayingMoviesViewModel> {
  error("No NowPlayingMoviesViewModel provided")
}

private val LocalPopularMoviesViewModel = staticCompositionLocalOf<PopularMoviesViewModel> {
  error("No PopularMoviesViewModel provided")
}

private val LocalMovieByIdViewModel = staticCompositionLocalOf<MovieByIdViewModel> {
  error("No MovieByIdViewModel provided")
}

private val LocalSelectedMovieViewModel = staticCompositionLocalOf<SelectedMovieViewModel> {
  error("No SelectedMovieViewModel provided")
}