package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.ComicSummaryEntity
import kotlinx.serialization.Serializable

@Serializable
data class ComicSummaryDTO(
    val resourceURI: String? = null,
    val name: String? = null
) {
    fun toDomain() = ComicSummaryEntity(
        resourceURI = resourceURI.orEmpty(),
        name = name.orEmpty()
    )
}