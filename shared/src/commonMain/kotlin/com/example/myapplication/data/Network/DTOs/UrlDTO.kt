package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.UrlEntity
import kotlinx.serialization.Serializable

@Serializable
data class UrlDTO(
    val type: String? = null,
    val url: String? = null
) {
    fun toDomain() = UrlEntity(
        type = type,
        url = url
    )
}
