package com.islam.music.features.main_screen.domain.usecases

import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MainScreenUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    suspend fun execute(): MainScreenResults {
        return when (val response = repository.getFavoriteList()) {

            is NetworkResponse.Success -> {
                response.data?.let {
                    MainScreenResults.ListLoaded(it) //TODO inject mapper
                } ?: MainScreenResults.UnExpectedError
            }
            is NetworkResponse.Failure -> {
                response.reason?.let {
                    MainScreenResults.Error(response.reason, response.httpCode)
                } ?: MainScreenResults.UnExpectedError
            }

        }

    }

}