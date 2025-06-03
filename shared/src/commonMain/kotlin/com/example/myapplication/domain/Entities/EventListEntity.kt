package com.example.myapplication.domain.Entities

data class EventListEntity(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<EventSummaryEntity>? = null
)