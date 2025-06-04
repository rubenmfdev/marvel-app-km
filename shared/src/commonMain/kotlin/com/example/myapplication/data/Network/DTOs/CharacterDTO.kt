package com.example.myapplication.data.Network.DTOs

import com.example.myapplication.domain.Entities.CharacterEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO(
    val id: Int? = null,
    val name: String? = null,
    @SerialName("description") val characterDescription: String? = null,
    val modified: String? = null,
    val resourceURI: String? = null,
    val urls: List<UrlDTO>? = null,
    val thumbnail: ImageDTO? = null,
    val comics: ComicListDTO? = null,
    val stories: StoryListDTO? = null,
    val events: EventListDTO? = null,
    val series: SeriesListDTO? = null
) {
    fun toDomain(): CharacterEntity {
        return CharacterEntity(
            id = id ?: 0,
            name = name.orEmpty(),
            characterDescription = characterDescription.orEmpty(),
            modified = modified.orEmpty(),
            resourceURI = resourceURI.orEmpty(),
            urls = urls?.map { it.toDomain() },
            thumbnail = thumbnail?.toDomain(),
            comics = comics?.toDomain(),
            stories = stories?.toDomain(),
            events = events?.toDomain(),
            series = series?.toDomain()
        )
    }
}
