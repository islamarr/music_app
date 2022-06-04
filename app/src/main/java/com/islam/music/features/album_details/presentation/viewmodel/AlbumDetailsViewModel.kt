package com.islam.music.features.album_details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.music.common.Resource
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.usecases.AlbumDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(private val useCase: AlbumDetailsUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow<AlbumDetailsStates>(AlbumDetailsStates.InitialState)
    val state: StateFlow<AlbumDetailsStates>
        get() = _state.asStateFlow()

    fun dispatch(action: AlbumDetailsActions) {
        handle(action).map { result ->
            reduce(result)
        }.onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }

    private suspend fun onViewState(state: AlbumDetailsStates) {
        _state.emit(state)
    }

    fun reduce(result: AlbumDetailsResults): AlbumDetailsStates =
        when (result) {
            is AlbumDetailsResults.UnExpectedError -> AlbumDetailsStates.ShowErrorMessage()
            is AlbumDetailsResults.Error -> AlbumDetailsStates.ShowErrorMessage(
                result.reason,
                result.errorCode
            )
            is AlbumDetailsResults.RemoteAlbumDetails -> AlbumDetailsStates.AlbumDetailsData(
                result.albumDetails
            )
            is AlbumDetailsResults.Loading -> AlbumDetailsStates.Loading
            is AlbumDetailsResults.LocalAlbumDetails -> TODO()
        }

    fun handle(actions: AlbumDetailsActions): Flow<AlbumDetailsResults> = flow {
        when (actions) {
            is AlbumDetailsActions.AlbumDetailsAction -> {
                emit(AlbumDetailsResults.Loading)
                emit(useCase.execute(actions.artistName, actions.albumName))
            }
            is AlbumDetailsActions.Save -> useCase.save(actions.albumEntity)
        }
    }
}