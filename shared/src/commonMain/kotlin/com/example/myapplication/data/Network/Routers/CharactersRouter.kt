package com.example.myapplication.data.Network.Routers

import com.example.myapplication.domain.Entities.Filters.CharacterFilterEntity
import com.example.myapplication.utils.Network.AuthType
import com.example.myapplication.utils.Network.BaseRouter
import io.ktor.http.HttpMethod
import com.example.myapplication.utils.Configuration

sealed class CharactersEndpoint {
    data class Character(val filters: CharacterFilterEntity) : CharactersEndpoint()
    data class CharacterWithId(val characterId: Int, val filters: CharacterFilterEntity) : CharactersEndpoint()
    data class CharacterComics(val characterId: Int, val filters: CharacterFilterEntity) : CharactersEndpoint()
    data class CharacterEvents(val characterId: Int, val filters: CharacterFilterEntity) : CharactersEndpoint()
    data class CharacterSeries(val characterId: Int, val filters: CharacterFilterEntity) : CharactersEndpoint()
    data class CharacterStories(val characterId: Int, val filters: CharacterFilterEntity) : CharactersEndpoint()
}

class CharactersRouter(
    private val endpoint: CharactersEndpoint
) : BaseRouter {

    private val commonPath = "characters"

    override val baseUrl: String = "${Configuration.baseUrl}$commonPath"

    override val path: String = when (endpoint) {
        is CharactersEndpoint.Character -> ""
        is CharactersEndpoint.CharacterWithId -> "${endpoint.characterId}"
        is CharactersEndpoint.CharacterComics -> "${endpoint.characterId}/comics"
        is CharactersEndpoint.CharacterEvents -> "${endpoint.characterId}/events"
        is CharactersEndpoint.CharacterSeries -> "${endpoint.characterId}/series"
        is CharactersEndpoint.CharacterStories -> "${endpoint.characterId}/stories"
    }

    override val method: HttpMethod = HttpMethod.Get

    override val headers: Map<String, String> = emptyMap()

    override val body: Any? = null

    override val queryParams: Map<String, String>? = when (endpoint) {
        is CharactersEndpoint.Character -> getFilterParams(endpoint.filters)
        is CharactersEndpoint.CharacterWithId -> getFilterParams(endpoint.filters)
        is CharactersEndpoint.CharacterComics -> getFilterParams(endpoint.filters)
        is CharactersEndpoint.CharacterEvents -> getFilterParams(endpoint.filters)
        is CharactersEndpoint.CharacterSeries -> getFilterParams(endpoint.filters)
        is CharactersEndpoint.CharacterStories -> getFilterParams(endpoint.filters)
    }

    override val authType: AuthType = AuthType.MARVEL

    private fun getFilterParams(filters: CharacterFilterEntity): Map<String, String> {
        val params = mutableMapOf<String, String>()
        filters.name?.let { params["name"] = it }
        filters.nameStartsWith?.let { params["nameStartsWith"] = it }
        filters.modifiedSince?.let { params["modifiedSince"] = it }
        filters.comics?.let { params["comics"] = it }
        filters.series?.let { params["series"] = it }
        filters.events?.let { params["events"] = it }
        filters.stories?.let { params["stories"] = it }
        filters.orderBy?.let { params["orderBy"] = it.value() }
        filters.limit?.let { params["limit"] = it.toString() }
        filters.offset?.let { params["offset"] = it.toString() }
        return params
    }
}

