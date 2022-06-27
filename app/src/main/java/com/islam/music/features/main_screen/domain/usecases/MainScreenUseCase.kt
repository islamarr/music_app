package com.islam.music.features.main_screen.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.main_screen.data.AlbumEntityToAlbumMapper
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenStates
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class MainScreenUseCase @Inject constructor(
    private val repository: AlbumDetailsRepository,
    private val albumEntityToAlbumMapper: AlbumEntityToAlbumMapper
) {

    suspend fun execute(): Flow<MainScreenStates> = flow {
        when (val response = repository.getFavoriteList()) {
            is DataResponse.Success -> {
                response.data?.collect {
                    if (it.isEmpty()) emit(MainScreenStates.EmptySavedList) else {
                        emit(MainScreenStates.SavedListLoaded(it.map { list ->
                            albumEntityToAlbumMapper.invoke(
                                list
                            )
                        }))
                    }
                }
            }
            is DataResponse.Failure -> {
                response.reason?.let {
                    emit(MainScreenStates.ShowErrorMessage(response.reason))
                } ?: emit(MainScreenStates.ShowErrorMessage())
            }
        }

    }

}