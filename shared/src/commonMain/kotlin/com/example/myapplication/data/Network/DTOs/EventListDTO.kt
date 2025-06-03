package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.EventListEntity
import kotlinx.serialization.Serializable

@Serializable
data class EventListDTO(
    val available: Int? = null,
    val returned: Int? = null,
    val collectionURI: String? = null,
    val items: List<EventSummaryDTO>? = null
) {
    fun toDomain() = EventListEntity(
        available = available ?: 0,
        returned = returned ?: 0,
        collectionURI = collectionURI.orEmpty(),
        items = items?.map { it.toDomain() }
    )
}