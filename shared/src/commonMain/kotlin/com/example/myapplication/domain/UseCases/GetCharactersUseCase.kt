package com.example.myapplication.domain.UseCases

import com.example.myapplication.domain.Entities.CharacterDataWrapperEntity
import com.example.myapplication.domain.Entities.Filters.CharacterFilterEntity
import com.example.myapplication.domain.Repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow

data class GetCharactersUseCaseInput(
    val filters: CharacterFilterEntity
)

interface GetCharactersUseCaseContract {
    fun execute(input: GetCharactersUseCaseInput): Flow<Result<CharacterDataWrapperEntity>>
}

class GetCharactersUseCase(
    private val repository: CharactersRepository
) : GetCharactersUseCaseContract {

    override fun execute(input: GetCharactersUseCaseInput): Flow<Result<CharacterDataWrapperEntity>> {
        return repository.getCharacters(filters = input.filters)
    }
}
