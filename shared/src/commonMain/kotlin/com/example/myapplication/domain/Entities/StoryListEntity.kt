package com.example.myapplication.domain.Entities

data class StoryListEntity(
    val available: Int? = null,
    val returned: Int? = null,
    val collectionURI: String? = null,
    val items: List<StorySummaryEntity>? = null
)