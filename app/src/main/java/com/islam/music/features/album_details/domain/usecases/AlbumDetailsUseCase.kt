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
                    AlbumDetailsStates.AlbumDetailsData(it, it.trackList.isEmpty())
                } ?: AlbumDetailsStates.ShowErrorMessage()
            }
            is DataResponse.Failure -> {
                response.reason?.let {
                    AlbumDetailsStates.ShowErrorMessage(response.reason)
                } ?: AlbumDetailsStates.ShowErrorMessage()
            }
        }

    }

    suspend fun getFavorite(artistName: String, albumName: String) : AlbumDetailsStates {
         return when (repository.getOneFavoriteAlbum(artistName, albumName)){
             is DataResponse.Success -> {
                 AlbumDetailsStates.SavedState(true)
             }
             is DataResponse.Failure -> {
                 AlbumDetailsStates.SavedState(false)
             }
         }
    }

    suspend fun setFavorite(isAdd: Boolean, albumEntity: AlbumEntity) {
        if (isAdd) repository.addToFavoriteList(albumEntity)
        else repository.removeFromFavoriteList(albumEntity)
    }

}