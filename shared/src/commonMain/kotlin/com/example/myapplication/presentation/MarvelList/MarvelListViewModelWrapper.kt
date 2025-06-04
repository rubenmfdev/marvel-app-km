package com.example.myapplication.presentation.MarvelList

import com.example.myapplication.domain.UseCases.GetCharactersUseCase
import com.example.myapplication.data.Repositories.CharactersDataRepository
import com.example.myapplication.data.Network.DataSources.CharactersNetworkDataSourceImpl
import com.example.myapplication.domain.Entities.CharacterEntity
import com.example.myapplication.utils.CommonStateFlow
import com.example.myapplication.utils.CommonStateFlowImpl
import com.example.myapplication.utils.Network.KtorNetworkManager
import com.example.myapplication.utils.LoaderState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MarvelListViewModelWrapper {

    private val scope = MainScope()
    private val viewModel = MarvelListViewModel(
        getCharactersUseCase = GetCharactersUseCase(
            repository = CharactersDataRepository(
                dataSource = CharactersNetworkDataSourceImpl(KtorNetworkManager())
            )
        ),
        coroutineScope = scope
    )

    private val _uiState = MutableStateFlow<MarvelListUiState>(MarvelListUiState.Loading)
    val state: CommonStateFlow<MarvelListUiState> = CommonStateFlowImpl(_uiState)

    init {
        scope.launch {
            viewModel.state.collect { loaderState ->
                _uiState.value = when (loaderState) {
                    is LoaderState.Loading -> MarvelListUiState.Loading
                    is LoaderState.Success -> MarvelListUiState.Success(loaderState.data)
                    is LoaderState.Failed -> MarvelListUiState.Error(loaderState.error.toString())
                }
            }
        }
    }

    fun onPullToRefresh() = viewModel.onPullToRefresh()
    fun onEndReached() = viewModel.onEndReached()
}

sealed class MarvelListUiState {
    object Loading : MarvelListUiState()
    data class Success(val characters: List<CharacterEntity>) : MarvelListUiState()
    data class Error(val message: String) : MarvelListUiState()
}
