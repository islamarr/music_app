package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.features.album_details.domain.entites.AlbumEntity


sealed class AlbumDetailsStates {
    object InitialState : AlbumDetailsStates()
    object Loading : AlbumDetailsStates()
    data class ShowErrorMessage(val reason: String? = null, val errorCode: Int? = 0) :
        AlbumDetailsStates()

    data class AlbumDetailsData(val albumDetails: AlbumEntity) :
        AlbumDetailsStates()
}
