package com.example.myapplication.data.Network.DataSources

import com.example.myapplication.data.Network.DTOs.CharacterDataWrapperDTO
import com.example.myapplication.data.Network.Routers.CharactersEndpoint
import com.example.myapplication.data.Network.Routers.CharactersRouter
import com.example.myapplication.domain.Entities.CharacterDataWrapperEntity
import com.example.myapplication.domain.Entities.Filters.CharacterFilterEntity
import com.example.myapplication.utils.Network.NetworkManager

interface CharactersNetworkDataSource {
    suspend fun getCharacters(filters: CharacterFilterEntity): Result<CharacterDataWrapperEntity>
    suspend fun getCharactersWithId(characterId: Int, filters: CharacterFilterEntity): Result<CharacterDataWrapperEntity>
}

class CharactersNetworkDataSourceImpl(
    private val networkManager: NetworkManager
) : CharactersNetworkDataSource {

    override suspend fun getCharacters(filters: CharacterFilterEntity): Result<CharacterDataWrapperEntity> {
        val router = CharactersRouter(CharactersEndpoint.Character(filters))
        return networkManager.requestObject(router, CharacterDataWrapperDTO.serializer())
            .map { it.toDomain() }
    }

    override suspend fun getCharactersWithId(characterId: Int, filters: CharacterFilterEntity): Result<CharacterDataWrapperEntity> {
        val router = CharactersRouter(CharactersEndpoint.CharacterWithId(characterId, filters))
        return networkManager.requestObject(router, CharacterDataWrapperDTO.serializer())
            .map { it.toDomain() }
    }
}
