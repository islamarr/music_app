package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.common.view.BaseViewModel
import com.islam.music.features.album_details.domain.usecases.AlbumDetailsUseCase
import com.islam.music.features.album_details.domain.usecases.GetFavoriteUseCase
import com.islam.music.features.album_details.domain.usecases.SetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val albumDetailsUseCase: AlbumDetailsUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase
) :
    BaseViewModel<AlbumDetailsStates, AlbumDetailsActions>(AlbumDetailsStates.InitialState) {

    private val _uiEvent: Channel<AlbumDetailsEvents> = Channel()
    val uiEvent = _uiEvent.receiveAsFlow()

    private suspend fun onViewEvent(event: AlbumDetailsEvents) {
        _uiEvent.send(event)
    }

    override fun handle(actions: AlbumDetailsActions): Flow<AlbumDetailsStates> = flow {
        when (actions) {
            is AlbumDetailsActions.AlbumDetailsAction -> {
                emit(AlbumDetailsStates.Loading)
                emit(getFavoriteUseCase.execute(actions.albumParams))
                emit(albumDetailsUseCase.execute(actions.albumParams))
            }
            is AlbumDetailsActions.SetFavoriteAction -> {
                setFavoriteUseCase.execute(
                    actions.isAdd,
                    actions.albumEntity
                )
                if (!actions.isAdd) {
                    onViewEvent(AlbumDetailsEvents.ShowUndoSaveSnackBar)
                }
            }
        }
    }

}