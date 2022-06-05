package com.islam.music.features.album_details.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsStates
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AlbumDetailsUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    suspend fun execute(
        artistName: String,//TODO add artistName + albumName in data class
        albumName: String
    ): AlbumDetailsStates {
        return when (val response = repository.getAlbumDetails(artistName, albumName)) {

            is DataResponse.Success -> {
                response.data?.let {
                    AlbumDetailsStates.AlbumDetailsData(it) //TODO inject mapper
                } ?: AlbumDetailsStates.ShowErrorMessage()
            }
            is DataResponse.Failure -> {
                response.reason?.let {
                    AlbumDetailsStates.ShowErrorMessage(response.reason)
                } ?: AlbumDetailsStates.ShowErrorMessage()
            }
        }

    }


    suspend fun save(albumEntity: AlbumEntity) {//TODO new use case
        repository.addToFavoriteList(albumEntity)
    }

}