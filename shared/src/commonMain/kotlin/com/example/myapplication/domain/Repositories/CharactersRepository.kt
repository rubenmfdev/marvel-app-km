package com.example.myapplication.domain.Repositories

import com.example.myapplication.domain.Entities.Filters.CharacterFilterEntity
import com.example.myapplication.domain.Entities.CharacterDataWrapperEntity
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(
        filters: CharacterFilterEntity
    ): Flow<Result<CharacterDataWrapperEntity>>

    fun getCharacterWithId(
        characterId: Int,
        filters: CharacterFilterEntity
    ): Flow<Result<CharacterDataWrapperEntity>>
}
