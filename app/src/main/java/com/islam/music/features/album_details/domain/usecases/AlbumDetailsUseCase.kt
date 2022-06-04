package com.islam.music.features.album_details.domain.usecases

import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AlbumDetailsUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    suspend fun save(albumEntity: AlbumEntity) {
        repository.addToFavoriteList(albumEntity)
    }

    suspend fun execute(
        artistName: String,//TODO add artistName + albumName in data class
        albumName: String
    ): AlbumDetailsResults {
        return when (val response = repository.getAlbumDetails(artistName, albumName)) {

            is NetworkResponse.Success -> {
                response.data?.let {
                    AlbumDetailsResults.RemoteAlbumDetails(it) //TODO inject mapper
                } ?: AlbumDetailsResults.UnExpectedError
            }
            is NetworkResponse.Failure -> {
                response.reason?.let {
                    AlbumDetailsResults.Error(response.reason, response.httpCode)
                } ?: AlbumDetailsResults.UnExpectedError
            }

        }

    }
}