package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.common.BaseViewModel
import com.islam.music.features.album_details.domain.usecases.AlbumDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(private val useCase: AlbumDetailsUseCase) :
    BaseViewModel<AlbumDetailsStates, AlbumDetailsActions, AlbumDetailsResults>(AlbumDetailsStates.InitialState) {

    override fun reduce(result: AlbumDetailsResults): AlbumDetailsStates =
        when (result) {
            is AlbumDetailsResults.UnExpectedError -> AlbumDetailsStates.ShowErrorMessage()
            is AlbumDetailsResults.Error -> AlbumDetailsStates.ShowErrorMessage(
                result.reason
            )
            is AlbumDetailsResults.RemoteAlbumDetails -> AlbumDetailsStates.AlbumDetailsData(
                result.albumDetails
            )
            is AlbumDetailsResults.Loading -> AlbumDetailsStates.Loading
            is AlbumDetailsResults.LocalAlbumDetails -> TODO()
        }

    override fun handle(actions: AlbumDetailsActions): Flow<AlbumDetailsResults> = flow {
        when (actions) {
            is AlbumDetailsActions.AlbumDetailsAction -> {
                emit(AlbumDetailsResults.Loading)
                emit(useCase.execute(actions.artistName, actions.albumName))
            }
            is AlbumDetailsActions.Save -> useCase.save(actions.albumEntity)
        }
    }
}