package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.common.Result
import com.islam.music.features.album_details.domain.entites.AlbumEntity

sealed class AlbumDetailsResults : Result{
    object Loading : AlbumDetailsResults()
    object UnExpectedError : AlbumDetailsResults()
    data class RemoteAlbumDetails(val albumDetails: AlbumEntity) : AlbumDetailsResults() //TODO Make sure all names is right
    data class Error(val reason: String) : AlbumDetailsResults()
}
