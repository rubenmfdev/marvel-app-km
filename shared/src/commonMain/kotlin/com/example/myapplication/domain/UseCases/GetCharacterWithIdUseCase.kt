package com.example.myapplication.domain.UseCases

import com.example.myapplication.domain.Entities.CharacterDataWrapperEntity
import com.example.myapplication.domain.Entities.Filters.CharacterFilterEntity
import com.example.myapplication.domain.Repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow

data class GetCharacterWithIdInput(
    val characterId: Int,
    val filters: CharacterFilterEntity
)

interface GetCharacterWithIdUseCaseContract {
    fun execute(input: GetCharacterWithIdInput): Flow<Result<CharacterDataWrapperEntity>>
}

class GetCharacterWithIdUseCase(
    private val repository: CharactersRepository
) : GetCharacterWithIdUseCaseContract {

    override fun execute(input: GetCharacterWithIdInput): Flow<Result<CharacterDataWrapperEntity>> {
        return repository.getCharacterWithId(
            characterId = input.characterId,
            filters = input.filters
        )
    }
}
