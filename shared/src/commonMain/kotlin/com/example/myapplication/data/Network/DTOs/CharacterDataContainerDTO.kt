package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.CharacterDataContainerEntity
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataContainerDTO(
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val count: Int? = null,
    val results: List<CharacterDTO>? = null
) {
    fun toDomain() = CharacterDataContainerEntity(
        offset = offset ?: 0,
        limit = limit ?: 0,
        total = total ?: 0,
        count = count ?: 0,
        results = results?.map { it.toDomain() }
    )
}