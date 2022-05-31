package com.islam.music.features.top_albums.presentation.viewmodel

sealed class TopAlbumsActions {
    data class LoadAllAlbums(val artistName: String) : TopAlbumsActions()
}
