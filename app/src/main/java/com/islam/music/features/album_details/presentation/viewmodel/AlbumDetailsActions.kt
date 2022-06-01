package com.islam.music.features.album_details.presentation.viewmodel

sealed class AlbumDetailsActions {
    data class AlbumDetailsAction(val artistName: String, val albumName: String) : AlbumDetailsActions()
}
