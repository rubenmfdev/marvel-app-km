package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.CharacterDataWrapperEntity
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataWrapperDTO(
    val code: Int? = null,
    val status: String? = null,
    val copyright: String? = null,
    val attributionText: String? = null,
    val attributionHTML: String? = null,
    val data: CharacterDataContainerDTO? = null,
    val etag: String? = null
) {
    fun toDomain() = CharacterDataWrapperEntity(
        code = code ?: 0,
        status = status.orEmpty(),
        copyright = copyright.orEmpty(),
        attributionText = attributionText.orEmpty(),
        attributionHTML = attributionHTML.orEmpty(),
        data = data?.toDomain(),
        etag = etag.orEmpty()
    )
}
