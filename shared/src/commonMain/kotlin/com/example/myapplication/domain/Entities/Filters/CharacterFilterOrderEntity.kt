package com.example.myapplication.domain.Entities.Filters

sealed class CharacterFilterOrderEntity {
    abstract fun value(): String

    data class Name(val ascendant: Boolean) : CharacterFilterOrderEntity() {
        override fun value(): String = if (ascendant) "name" else "-name"
    }

    data class Modified(val ascendant: Boolean) : CharacterFilterOrderEntity() {
        override fun value(): String = if (ascendant) "modified" else "-modified"
    }
}
