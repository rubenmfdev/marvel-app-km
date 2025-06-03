package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.SeriesListEntity
import kotlinx.serialization.Serializable

@Serializable
data class SeriesListDTO(
    val available: Int? = null,
    val returned: Int? = null,
    val collectionURI: String? = null,
    val items: List<SeriesSummaryDTO>? = null
) {
    fun toDomain() = SeriesListEntity(
        available = available ?: 0,
        returned = returned ?: 0,
        collectionURI = collectionURI.orEmpty(),
        items = items?.map { it.toDomain() }
    )
}