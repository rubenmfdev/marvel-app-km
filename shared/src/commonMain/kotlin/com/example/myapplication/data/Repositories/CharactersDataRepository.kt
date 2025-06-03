package com.example.myapplication.data.Repositories

import com.example.myapplication.data.Network.DataSources.CharactersNetworkDataSource
import com.example.myapplication.domain.Entities.CharacterDataWrapperEntity
import com.example.myapplication.domain.Entities.Filters.CharacterFilterEntity
import com.example.myapplication.domain.Repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersDataRepository(
    private val dataSource: CharactersNetworkDataSource
) : CharactersRepository {

    override fun getCharacters(filters: CharacterFilterEntity): Flow<Result<CharacterDataWrapperEntity>> = flow {
        emit(dataSource.getCharacters(filters))
    }

    override fun getCharacterWithId(
        characterId: Int,
        filters: CharacterFilterEntity
    ): Flow<Result<CharacterDataWrapperEntity>> = flow {
        emit(dataSource.getCharactersWithId(characterId, filters))
    }
}
