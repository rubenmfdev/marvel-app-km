package com.example.myapplication.domain.Entities

data class CharacterDataContainerEntity(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterEntity>? = null
)