package com.islam.music.features.top_albums.presentation.viewmodel

import com.islam.music.features.top_albums.domain.entites.Album

sealed class TopAlbumsResults {
    object Loading : TopAlbumsResults()
    object UnExpectedError : TopAlbumsResults()
    object TopAlbumsEmptyList : TopAlbumsResults()
    data class TopAlbumsListLoaded(val topAlbumsList: List<Album>) : TopAlbumsResults()
    data class Error(val reason: String) : TopAlbumsResults()
}
