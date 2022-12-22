package com.omk.MainScreen.ui.dashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.omk.MainScreen.domain.model.Movie
import com.omk.MainScreen.ui.components.Card
import com.omk.MainScreen.ui.components.HighlightMovieItem
import com.omk.MainScreen.ui.components.Surface
import com.omk.MainScreen.ui.dashboard.home.component.InfoButton
import com.omk.MainScreen.ui.dashboard.home.component.TopRatedMovies
import com.omk.MainScreen.ui.dashboard.home.component.MyListButton
import com.omk.MainScreen.ui.dashboard.home.component.PlayButton
import com.omk.MainScreen.ui.dashboard.home.component.PopularMovies
import com.omk.MainScreen.ui.dashboard.home.component.TrendingNow
import com.omk.MainScreen.ui.dashboard.home.component.onBottomSheetTapped
import com.omk.MainScreen.ui.theme.AppTheme
import com.omk.MainScreen.ui.viewmodel.ViewModelProvider
import com.omk.MainScreen.util.Resource.Success
import kotlinx.coroutines.CoroutineScope

@ExperimentalMaterialApi
@Composable
fun Home(
  bottomSheetScaffoldState: BottomSheetScaffoldState,
  modifier: Modifier = Modifier,
  coroutineScope: CoroutineScope,
  scrollState: LazyListState
) {
  val selectedMovieViewModel = ViewModelProvider.selectedMovieViewModel
  Surface(
    color = AppTheme.colors.appBackground,
  ) {
    LazyColumn(
      state = scrollState,
      modifier = modifier
        .padding(bottom = 120.dp)
    ) {
      item {
        when (val topHighlightedMovie = ViewModelProvider.movieByIdViewModel.movie) {
          is Success -> {
            TopHighlightedMovie(
              onMovieClick = {
                selectedMovieViewModel.setSelectedMovie(topHighlightedMovie.data)
                onBottomSheetTapped(
                  coroutineScope = coroutineScope,
                  bottomSheetScaffoldState = bottomSheetScaffoldState
                )
              },
              modifier = modifier,
              topMovie = topHighlightedMovie.data
            )
          }
        }
        Spacer(modifier = Modifier.height(10.dp))
        when (val topRatedMovies = ViewModelProvider.topRatedMoviesViewModel.topRatedMovies) {
          is Success -> {
            TopRatedMovies(
              onMovieClick = { movieId ->
                selectedMovieViewModel.setSelectedMovie(topRatedMovies.data.find { it.id == movieId })
                onBottomSheetTapped(
                  coroutineScope = coroutineScope,
                  bottomSheetScaffoldState = bottomSheetScaffoldState
                )
              },
              modifier = modifier,
              topRatedMovies = topRatedMovies.data
            )
          }
        }
        Spacer(modifier = Modifier.height(20.dp))
        when (val popularMovies = ViewModelProvider.popularMoviesViewModel.popularMovies) {
          is Success -> {
            PopularMovies(
              onMovieClick = { movieId ->
                selectedMovieViewModel.setSelectedMovie(popularMovies.data.find { it.id == movieId })
                onBottomSheetTapped(
                  coroutineScope = coroutineScope,
                  bottomSheetScaffoldState = bottomSheetScaffoldState
                )
              },
              modifier = modifier,
              popularOnJetFlixMovies = popularMovies.data
            )
          }
        }
        Spacer(modifier = Modifier.height(20.dp))
        when (val trendingNow = ViewModelProvider.nowPlayingMoviesViewModel.nowPlayingMovies) {
          is Success -> {
            TrendingNow(
              onMovieClick = { movieId ->
                selectedMovieViewModel.setSelectedMovie(trendingNow.data.find { it.id == movieId })
                onBottomSheetTapped(
                  coroutineScope = coroutineScope,
                  bottomSheetScaffoldState = bottomSheetScaffoldState
                )
              },
              modifier = modifier,
              trendingNowMovies = trendingNow.data
            )
          }
        }
      }
    }
  }
}

@Composable
private fun TopHighlightedMovie(
  onMovieClick: (Long) -> Unit,
  modifier: Modifier = Modifier,
  topMovie: Movie
) {
  ConstraintLayout {
    // Create references for the composables to constrain
    val (movieImage, buttonPanel, topTrendingBanner) = createRefs()
    HighlightMovieItem(topMovie, onMovieClick,
      modifier = modifier.constrainAs(movieImage) {
        top.linkTo(parent.top)
      }
    )
    TopTrendingBanner(
      modifier = modifier.constrainAs(topTrendingBanner) {
        bottom.linkTo(buttonPanel.top, margin = 24.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
      }
    )
    Row(
      modifier = modifier
        .constrainAs(buttonPanel) {
          bottom.linkTo(parent.bottom, margin = 32.dp)
        }
    ) {
      MyListButton(modifier = modifier.weight(1f))
      PlayButton(isPressed = mutableStateOf(true), modifier = modifier.weight(1f))
      InfoButton(modifier = modifier.weight(1f))
    }
  }
}

@Composable
private fun TopTrendingBanner(
  modifier: Modifier
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Card(
      color = AppTheme.colors.banner,
      shape = RoundedCornerShape(10),
      modifier = modifier
        .size(
          width = 28.dp,
          height = 28.dp
        )
    ) {
      Column(
        modifier = Modifier
          .padding(2.dp)
          .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "TOP",
          fontSize = 8.sp,
          style = MaterialTheme.typography.button,
          maxLines = 1
        )
        Text(
          text = "10",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          style = MaterialTheme.typography.button,
          maxLines = 1
        )
      }
    }
    Spacer(modifier = Modifier.width(4.dp))
    Text(
      text = "#2 in India Today",
      color = AppTheme.colors.textPrimary,
      fontSize = 14.sp,
      letterSpacing = (-0.10).sp,
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.button,
      maxLines = 1
    )
  }
}