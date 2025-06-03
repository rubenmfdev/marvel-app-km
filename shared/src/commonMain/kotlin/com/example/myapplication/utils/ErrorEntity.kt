package com.example.myapplication.utils

sealed class ErrorEntity : Throwable() {
    object Network : ErrorEntity()
    object Database : ErrorEntity()
    object Generic : ErrorEntity()
}
