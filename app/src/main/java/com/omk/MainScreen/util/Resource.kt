package com.omk.MainScreen.util

sealed class Resource<out T> {
  data class Success<out T>(val data: T) : Resource<T>()
  data class Error(val error: com.omk.MainScreen.util.Error) : Resource<Nothing>()
  object Loading : Resource<Nothing>()
}