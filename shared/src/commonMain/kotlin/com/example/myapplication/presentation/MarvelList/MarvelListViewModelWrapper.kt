package com.example.myapplication.presentation.MarvelList

import com.example.myapplication.domain.UseCases.GetCharactersUseCase
import com.example.myapplication.data.Repositories.CharactersDataRepository
import com.example.myapplication.data.Network.DataSources.CharactersNetworkDataSourceImpl
import com.example.myapplication.utils.Network.KtorNetworkManager
import com.example.myapplication.utils.LoaderState
import kotlinx.coroutines.MainScope

class MarvelListViewModelWrapper {
    private val viewModel = MarvelListViewModel(
        getCharactersUseCase = GetCharactersUseCase(
            repository = CharactersDataRepository(
                dataSource = CharactersNetworkDataSourceImpl(
                    networkManager = KtorNetworkManager()
                )
            )
        ),
        coroutineScope = MainScope()
    )

    fun onPullToRefresh() {
        viewModel.onPullToRefresh()
    }

    fun onEndReached() {
        viewModel.onEndReached()
    }

    // Expose a simplified state as a string (or JSON or serialized)
    fun currentState(): String {
        return when (val state = viewModel.state.value) {
            is LoaderState.Loading -> "loading"
            is LoaderState.Success -> "success: ${state.data.size} characters"
            is LoaderState.Failed -> "error: ${state.error}"
        }
    }
}
