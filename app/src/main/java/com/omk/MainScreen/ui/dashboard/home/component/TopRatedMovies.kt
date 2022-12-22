package com.omk.MainScreen.ui.dashboard.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omk.MainScreen.domain.model.Movie
import com.omk.MainScreen.ui.components.LargeMovieItem
import com.omk.MainScreen.ui.theme.AppTheme

@Composable
fun TopRatedMovies(
    onMovieClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    topRatedMovies: List<Movie>
) {
  Column(modifier = modifier) {
    Text(
      text = "Top Rated Movies",
      style = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.25.sp
      ),
      color = AppTheme.colors.textPrimary,
      modifier = Modifier.padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.height(12.dp))
    TrendingNowMoviesCarousel(movies = topRatedMovies, onMovieSelected = onMovieClick)
  }
}

@Composable
private fun TrendingNowMoviesCarousel(
  movies: List<Movie>,
  onMovieSelected: (Long) -> Unit
) {
  LazyRow(modifier = Modifier.padding(start = 8.dp)) {
    items(movies) { movie ->
      LargeMovieItem(movie, onMovieSelected = onMovieSelected)
      Spacer(modifier = Modifier.width(8.dp))
    }
  }
}