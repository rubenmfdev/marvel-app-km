package com.example.myapplication.domain.Entities

data class ImageEntity(
    val path: String,
    val extension: String
) {
    val fullPath: String
        get() = "$path.$extension"
}