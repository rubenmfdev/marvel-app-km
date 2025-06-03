package com.example.myapplication.data.Network.DTOs
import com.example.myapplication.domain.Entities.ComicListEntity
import kotlinx.serialization.Serializable

@Serializable
data class ComicListDTO(
    val available: Int? = null,
    val returned: Int? = null,
    val collectionURI: String? = null,
    val items: List<ComicSummaryDTO>? = null
) {
    fun toDomain() = ComicListEntity(
        available = available ?: 0,
        returned = returned ?: 0,
        collectionURI = collectionURI.orEmpty(),
        items = items?.map { it.toDomain() }
    )
}
