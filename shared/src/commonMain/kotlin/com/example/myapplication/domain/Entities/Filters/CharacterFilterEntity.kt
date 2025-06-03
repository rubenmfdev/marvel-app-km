package com.example.myapplication.domain.Entities.Filters

data class CharacterFilterEntity(
    val name: String? = null,
    val nameStartsWith: String? = null,
    val modifiedSince: String? = null,
    val comics: String? = null,
    val series: String? = null,
    val events: String? = null,
    val stories: String? = null,
    val orderBy: CharacterFilterOrderEntity? = null,
    val limit: Int? = null,
    var offset: Int? = null
)
