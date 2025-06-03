package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.ImageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDTO(
    val path: String? = null,
    @SerialName("extension") val imageExtension: String? = null
) {
    fun toDomain() = ImageEntity(
        path = path.orEmpty(),
        extension = imageExtension.orEmpty()
    )
}