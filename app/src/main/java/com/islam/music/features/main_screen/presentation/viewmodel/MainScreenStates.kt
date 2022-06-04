package com.islam.music.features.main_screen.presentation.viewmodel

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.top_albums.domain.entites.Album


sealed class MainScreenStates {
    object InitialState : MainScreenStates()
    object Loading : MainScreenStates()
    data class ShowErrorMessage(val reason: String? = null) :
        MainScreenStates()

    data class TopAlbumsListLoaded(val topAlbumsList: List<Album>) : MainScreenStates()
    object EmptyTopAlbumsList : MainScreenStates()
}
