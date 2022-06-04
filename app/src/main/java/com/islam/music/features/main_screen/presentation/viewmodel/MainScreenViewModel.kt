package com.islam.music.features.main_screen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.music.features.album_details.domain.usecases.AlbumDetailsUseCase
import com.islam.music.features.main_screen.domain.usecases.MainScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val useCase: MainScreenUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow<MainScreenStates>(MainScreenStates.InitialState)
    val state: StateFlow<MainScreenStates>
        get() = _state.asStateFlow()

    fun dispatch(action: MainScreenActions) {
        handle(action).map { result ->
            reduce(result)
        }.onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }

    private suspend fun onViewState(state: MainScreenStates) {
        _state.emit(state)
    }

    fun reduce(result: MainScreenResults): MainScreenStates =
        when (result) {
            is MainScreenResults.UnExpectedError -> MainScreenStates.ShowErrorMessage()
            is MainScreenResults.Error -> MainScreenStates.ShowErrorMessage(
                result.reason,
                result.errorCode
            )
            is MainScreenResults.Loading -> MainScreenStates.Loading
            is MainScreenResults.EmptyList -> MainScreenStates.EmptyTopAlbumsList
            is MainScreenResults.ListLoaded -> MainScreenStates.TopAlbumsListLoaded(result.topAlbumsList)
        }

    fun handle(actions: MainScreenActions): Flow<MainScreenResults> = flow {
        when (actions) {
            is MainScreenActions.LoadAllSavedAlbums -> {
                emit(MainScreenResults.Loading)
                emit(useCase.execute())
            }
        }
    }
}