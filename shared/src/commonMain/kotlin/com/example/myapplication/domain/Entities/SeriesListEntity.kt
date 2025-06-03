package com.example.myapplication.domain.Entities

data class SeriesListEntity(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<SeriesSummaryEntity>? = null
)