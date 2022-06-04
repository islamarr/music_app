package com.islam.music.features.main_screen.presentation.viewmodel

import com.islam.music.common.BaseViewModel
import com.islam.music.features.main_screen.domain.usecases.MainScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val useCase: MainScreenUseCase) :
    BaseViewModel<MainScreenStates, MainScreenActions, MainScreenResults>(MainScreenStates.InitialState) {

    override fun reduce(result: MainScreenResults): MainScreenStates =
        when (result) {
            is MainScreenResults.UnExpectedError -> MainScreenStates.ShowErrorMessage()
            is MainScreenResults.Error -> MainScreenStates.ShowErrorMessage(result.reason)
            is MainScreenResults.Loading -> MainScreenStates.Loading
            is MainScreenResults.EmptyList -> MainScreenStates.EmptyTopAlbumsList
            is MainScreenResults.ListLoaded -> MainScreenStates.TopAlbumsListLoaded(result.topAlbumsList)
        }

    override fun handle(actions: MainScreenActions): Flow<MainScreenResults> = flow {
        when (actions) {
            is MainScreenActions.LoadAllSavedAlbums -> {
                emit(MainScreenResults.Loading)
                emit(useCase.execute())
            }
        }
    }
}