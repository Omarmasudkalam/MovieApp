package com.omk.MainScreen.domain.repo

import com.omk.MainScreen.domain.model.Movie
import com.omk.MainScreen.domain.model.MovieVideo
import com.omk.MainScreen.util.Single
import com.omk.MainScreen.util.Error

interface MoviesRepository {

  suspend fun getTopRatedMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>>

  suspend fun getNowPlayingMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>>

  suspend fun getPopularMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>>

  suspend fun getMovieById(
    movieId: Long
  ): Single<Error, Movie>

  suspend fun getMovieVideosById(
    movieId: Long
  ): Single<Error, MovieVideo>

  suspend fun getSimilarMovies(
    movieId: Long
  ): Single<Error, List<Movie>>
}