package com.example.myapplication.android.MarvelList

import androidx.lifecycle.ViewModel
import com.example.myapplication.presentation.MarvelList.MarvelListViewModel
import com.example.myapplication.domain.UseCases.GetCharactersUseCaseContract
import kotlinx.coroutines.MainScope

class MarvelListViewModelAndroid(
    getCharactersUseCase: GetCharactersUseCaseContract
) : ViewModel() {

    val delegate = MarvelListViewModel(
        getCharactersUseCase = getCharactersUseCase,
        coroutineScope = MainScope()
    )
}
