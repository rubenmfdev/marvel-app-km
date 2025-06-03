package com.example.myapplication.utils

sealed class LoaderState<out T> {
    object Loading : LoaderState<Nothing>()
    data class Success<T>(val data: T) : LoaderState<T>()
    data class Failed(val error: ErrorEntity) : LoaderState<Nothing>()
}