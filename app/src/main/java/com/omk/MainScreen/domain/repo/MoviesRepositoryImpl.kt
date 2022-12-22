package com.omk.MainScreen.domain.repo

import com.omk.MainScreen.data.network.constant.MoviesApi
import com.omk.MainScreen.data.network.service.MoviesService
import com.omk.MainScreen.domain.model.Movie
import com.omk.MainScreen.domain.model.MovieVideo
import com.omk.MainScreen.util.Error
import com.omk.MainScreen.util.Error.UnexpectedError
import com.omk.MainScreen.util.Single
import com.omk.MainScreen.util.error
import com.omk.MainScreen.util.success
import timber.log.Timber

class MoviesRepositoryImpl(
  private val service: MoviesService
) : MoviesRepository {

  companion object {
    private const val TAG = "MoviesRepo"
  }

  override suspend fun getTopRatedMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>> {
    return try {
      val result = service.getTopRatedMovies(
        language = language,
        page = page,
        apiKey = MoviesApi.api_Key
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }

  // Use for Trending Now
  override suspend fun getNowPlayingMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>> {
    return try {
      val result = service.getNowPlayingMovies(
        language = language,
        page = page,
        apiKey = MoviesApi.api_Key
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }


  override suspend fun getPopularMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>> {
    return try {
      val result = service.getPopularMovies(
        language = language,
        page = page,
        apiKey = MoviesApi.api_Key
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }

  override suspend fun getMovieById(
    movieId: Long
  ): Single<Error, Movie> {
    return try {
      val result = service.getMovieById(
        movieId = movieId,
        apiKey = MoviesApi.api_Key
      ).asDomainModel()
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }

  override suspend fun getMovieVideosById(
    movieId: Long
  ): Single<Error, MovieVideo> {
    return try {
      val result = service.getMovieVideosById(
        movieId = movieId,
        apiKey = MoviesApi.api_Key
      ).results.first().asDomainModel()
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }

  override suspend fun getSimilarMovies(
    movieId: Long
  ): Single<Error, List<Movie>> {
    return try {
      val result = service.getSimilarMovies(
        movieId = movieId,
        apiKey = MoviesApi.api_Key
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }
}