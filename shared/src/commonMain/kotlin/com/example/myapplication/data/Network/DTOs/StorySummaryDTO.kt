package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.StorySummaryEntity
import kotlinx.serialization.Serializable

@Serializable
data class StorySummaryDTO(
    val resourceURI: String? = null,
    val name: String? = null,
    val type: String? = null
) {
    fun toDomain() = StorySummaryEntity(
        resourceURI = resourceURI.orEmpty(),
        name = name.orEmpty(),
        type = type.orEmpty()
    )
}