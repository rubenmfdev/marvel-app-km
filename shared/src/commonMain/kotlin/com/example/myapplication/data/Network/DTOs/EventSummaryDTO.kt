package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.EventSummaryEntity
import kotlinx.serialization.Serializable

@Serializable
data class EventSummaryDTO(
    val resourceURI: String? = null,
    val name: String? = null
) {
    fun toDomain() = EventSummaryEntity(
        resourceURI = resourceURI.orEmpty(),
        name = name.orEmpty()
    )
}