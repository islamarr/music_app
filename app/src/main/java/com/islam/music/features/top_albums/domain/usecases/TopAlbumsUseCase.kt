package com.islam.music.features.top_albums.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class TopAlbumsUseCase @Inject constructor(private val repository: TopAlbumsRepository) {

    suspend fun execute(artistName: String): TopAlbumsResults {
        return when (val response = repository.getTopAlbums(artistName)) {
            is DataResponse.Success -> {
                response.data?.let {
                    if (it.topAlbums.albums.isEmpty()) TopAlbumsResults.TopAlbumsEmptyList else
                        TopAlbumsResults.TopAlbumsListLoaded(it.topAlbums.albums)
                } ?: TopAlbumsResults.UnExpectedError
            }
            is DataResponse.Failure -> {
                response.reason?.let {
                    TopAlbumsResults.Error(response.reason)
                } ?: TopAlbumsResults.UnExpectedError
            }
        }
    }

}