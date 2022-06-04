package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.features.album_details.domain.entites.AlbumEntity

sealed class AlbumDetailsResults {
    object Loading : AlbumDetailsResults()
    object UnExpectedError : AlbumDetailsResults()
    data class RemoteAlbumDetails(val albumDetails: AlbumEntity) : AlbumDetailsResults()
    data class LocalAlbumDetails(val albumDetails: AlbumEntity) : AlbumDetailsResults()
    data class Error(val reason: String) : AlbumDetailsResults()
}
