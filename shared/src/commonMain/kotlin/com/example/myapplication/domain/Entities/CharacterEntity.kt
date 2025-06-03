package com.example.myapplication.domain.Entities

data class CharacterEntity(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<UrlEntity>?,
    val thumbnail: ImageEntity?,
    val comics: ComicListEntity?,
    val stories: StoryListEntity?,
    val events: EventListEntity?,
    val series: SeriesListEntity?
) {
    val thumbnailUrl: String
        get() = thumbnail?.let { "${it.path}.${it.extension}" } ?: ""

    companion object {
        val mock = CharacterEntity(
            id = 0,
            name = "Comic test title & Avengers",
            description = "Test description",
            modified = "17/08/1997",
            resourceURI = "",
            urls = null,
            thumbnail = null,
            comics = null,
            stories = null,
            events = null,
            series = null
        )

        fun mockList(size: Int = 10): List<CharacterEntity> {
            return List(size) { mock }
        }
    }
}