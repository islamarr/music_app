package com.islam.music.features.top_albums.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.music.features.top_albums.domain.usecases.TopAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TopAlbumsViewModel @Inject constructor(private val useCase: TopAlbumsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<TopAlbumsStates>(TopAlbumsStates.InitialState)
    val state: StateFlow<TopAlbumsStates>
        get() = _state.asStateFlow()

    fun dispatch(action: TopAlbumsActions) {
        handle(action).map { result ->
            reduce(result)
        }.onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }

    private suspend fun onViewState(state: TopAlbumsStates) {
        _state.emit(state)
    }

    fun reduce(result: TopAlbumsResults): TopAlbumsStates =
        when (result) {
            is TopAlbumsResults.UnExpectedError -> TopAlbumsStates.ShowErrorMessage()
            is TopAlbumsResults.Error -> TopAlbumsStates.ShowErrorMessage(
                result.reason)
            is TopAlbumsResults.TopAlbumsListLoaded -> TopAlbumsStates.TopAlbumsListLoaded(result.topAlbumsList)
            is TopAlbumsResults.TopAlbumsEmptyList -> TopAlbumsStates.EmptyTopAlbumsList
            is TopAlbumsResults.Loading -> TopAlbumsStates.Loading
        }

    fun handle(actions: TopAlbumsActions): Flow<TopAlbumsResults> = flow {
        when (actions) {
            is TopAlbumsActions.LoadAllAlbums -> {
                emit(TopAlbumsResults.Loading)
                emit(useCase.execute(actions.artistName))
            }
        }
    }

}