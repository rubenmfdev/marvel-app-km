package com.example.myapplication.domain.Entities

data class ComicListEntity(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<ComicSummaryEntity>? = null
)