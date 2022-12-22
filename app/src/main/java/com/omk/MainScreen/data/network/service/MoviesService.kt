package com.omk.MainScreen.data.network.service

import com.omk.MainScreen.data.network.constant.MoviesApi
import com.omk.MainScreen.data.network.model.MovieDetailsDto
import com.omk.MainScreen.data.network.model.MoviesDto
import com.omk.MainScreen.data.network.model.MoviesVideosDto
import com.omk.MainScreen.data.network.model.SimilarMoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
  @GET(MoviesApi.ENDPOINT_TOP_RATED)
  suspend fun getTopRatedMovies(
    @Query("language") language: String,
    @Query("page") page: Int,
    @Query("api_key") apiKey: String,
  ): MoviesDto

  @GET(MoviesApi.ENDPOINT_NOW_PLAYING)
  suspend fun getNowPlayingMovies(
    @Query("language") language: String,
    @Query("page") page: Int,
    @Query("api_key") apiKey: String,
  ): MoviesDto

  @GET(MoviesApi.ENDPOINT_POPULAR)
  suspend fun getPopularMovies(
    @Query("language") language: String,
    @Query("page") page: Int,
    @Query("api_key") apiKey: String,
  ): MoviesDto

  @GET(MoviesApi.ENDPOINT_MOVIE)
  suspend fun getMovieById(
    @Path("movie_id") movieId: Long,
    @Query("api_key") apiKey: String
  ): MovieDetailsDto

  @GET(MoviesApi.ENDPOINT_MOVIE_VIDEO)
  suspend fun getMovieVideosById(
    @Path("movie_id") movieId: Long,
    @Query("api_key") apiKey: String
  ): MoviesVideosDto

  @GET(MoviesApi.ENDPOINT_SIMILAR_MOVIES)
  suspend fun getSimilarMovies(
    @Path("movie_id") movieId: Long,
    @Query("api_key") apiKey: String
  ): SimilarMoviesDto
}