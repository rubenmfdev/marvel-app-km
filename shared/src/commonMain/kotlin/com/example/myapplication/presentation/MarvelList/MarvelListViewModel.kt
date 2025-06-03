package com.example.myapplication.presentation.MarvelList

import com.example.myapplication.domain.Entities.CharacterEntity
import com.example.myapplication.domain.Entities.Filters.CharacterFilterEntity
import com.example.myapplication.domain.UseCases.GetCharactersUseCaseContract
import com.example.myapplication.domain.UseCases.GetCharactersUseCaseInput
import com.example.myapplication.utils.ErrorEntity
import com.example.myapplication.utils.LoaderState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MarvelListViewModel(
    private val getCharactersUseCase: GetCharactersUseCaseContract,
    private val coroutineScope: CoroutineScope
) {

    private var characters = mutableListOf<CharacterEntity>()
    private var filter = CharacterFilterEntity()
    private var hasMoreData = true
    private var isLoadingData = false

    private val _state: MutableStateFlow<LoaderState<List<CharacterEntity>>> =
        MutableStateFlow(LoaderState.Loading)
    val state: StateFlow<LoaderState<List<CharacterEntity>>> = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun onPullToRefresh() {
        _state.value = LoaderState.Loading
        resetCharacters()
        loadCharacters()
    }

    fun onEndReached() {
        if (_state.value is LoaderState.Success && hasMoreData && !isLoadingData) {
            loadCharacters()
        }
    }

    private fun loadCharacters() {
        isLoadingData = true
        coroutineScope.launch {
            getCharactersUseCase.execute(GetCharactersUseCaseInput(filters = filter))
                .collect { result ->
                    result.fold(
                        onSuccess = { wrapper ->
                            val results = wrapper.data?.results.orEmpty()
                            val count = wrapper.data?.count ?: 0
                            filter.offset = (filter.offset ?: 0) + count
                            characters.addAll(results)
                            _state.value = LoaderState.Success(characters.toList())
                            hasMoreData = results.isNotEmpty()
                        },
                        onFailure = { error ->
                            _state.value = LoaderState.Failed(error as? ErrorEntity ?: ErrorEntity.Generic)
                        }
                    )
                    isLoadingData = false
                }
        }
    }

    private fun resetCharacters() {
        characters.clear()
        filter = CharacterFilterEntity()
        hasMoreData = true
        isLoadingData = false
    }
}