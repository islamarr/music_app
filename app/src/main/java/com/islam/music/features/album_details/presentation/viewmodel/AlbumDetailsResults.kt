package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.features.album_details.domain.entites.AlbumDetails

sealed class AlbumDetailsResults {
    object Loading : AlbumDetailsResults()
    object UnExpectedError : AlbumDetailsResults()
    data class AlbumsDetails(val albumsDetails: AlbumDetails) : AlbumDetailsResults()
    data class Error(val reason: String, val errorCode: Int) : AlbumDetailsResults()
}
