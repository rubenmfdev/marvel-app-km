package com.example.myapplication.android.MarvelList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.presentation.ViewModels.MarvelListViewModel
import com.example.myapplication.domain.UseCases.GetCharactersUseCase
import com.example.myapplication.data.Repositories.CharactersDataRepository
import com.example.myapplication.data.Network.DataSources.CharactersNetworkDataSourceImpl
import com.example.myapplication.utils.Network.KtorNetworkManager
import kotlinx.coroutines.MainScope

class MarvelListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dataSource = CharactersNetworkDataSourceImpl(KtorNetworkManager())
        val repository = CharactersDataRepository(dataSource)
        val useCase = GetCharactersUseCase(repository)

        return MarvelListViewModelAndroid(useCase) as T
    }
}
