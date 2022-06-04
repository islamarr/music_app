package com.islam.music.features.main_screen.domain.usecases

import com.islam.music.common.DataResponse
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsResults
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MainScreenUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    suspend fun execute(): MainScreenResults {
        return when (val response = repository.getFavoriteList()) {

            is DataResponse.Success -> {
                response.data?.let {
                    MainScreenResults.ListLoaded(it) //TODO inject mapper
                } ?: MainScreenResults.UnExpectedError
            }
            is DataResponse.Failure -> {
                response.reason?.let {
                    MainScreenResults.Error(response.reason)
                } ?: MainScreenResults.UnExpectedError
            }
        }

    }

}