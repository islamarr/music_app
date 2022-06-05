package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.common.view.BaseViewModel
import com.islam.music.features.album_details.domain.usecases.AlbumDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(private val useCase: AlbumDetailsUseCase) :
    BaseViewModel<AlbumDetailsStates, AlbumDetailsActions>(AlbumDetailsStates.InitialState) {

    override fun handle(actions: AlbumDetailsActions): Flow<AlbumDetailsStates> = flow {
        when (actions) {
            is AlbumDetailsActions.AlbumDetailsAction -> {
                emit(AlbumDetailsStates.Loading)
                emit(useCase.execute(actions.artistName, actions.albumName))
            }
            is AlbumDetailsActions.Save -> useCase.save(actions.albumEntity)
        }
    }

}