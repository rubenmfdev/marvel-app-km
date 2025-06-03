package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.StoryListEntity
import kotlinx.serialization.Serializable

@Serializable
data class StoryListDTO(
    val available: Int? = null,
    val returned: Int? = null,
    val collectionURI: String? = null,
    val items: List<StorySummaryDTO>? = null
) {
    fun toDomain() = StoryListEntity(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = items?.map { it.toDomain() }
    )
}