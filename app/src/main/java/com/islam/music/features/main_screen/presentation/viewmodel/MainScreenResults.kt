package com.islam.music.features.main_screen.presentation.viewmodel

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.top_albums.domain.entites.Album

sealed class MainScreenResults {
    object Loading : MainScreenResults()
    object UnExpectedError : MainScreenResults()
    object EmptyList : MainScreenResults()
    data class ListLoaded(val topAlbumsList: List<Album>) : MainScreenResults()
    data class Error(val reason: String, val errorCode: Int) : MainScreenResults()
}
