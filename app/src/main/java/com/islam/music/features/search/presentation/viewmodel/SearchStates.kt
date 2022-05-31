package com.islam.music.features.search.presentation.viewmodel

import com.islam.music.features.search.domain.entites.Artist


sealed class SearchStates {
    object InitialState : SearchStates()
    object Loading : SearchStates()
    data class ShowErrorMessage(val reason: String? = null, val errorCode: Int? = 0) :
        SearchStates()
    data class ArtistListLoaded(val artistList: List<Artist>) : SearchStates()
    object EmptyArtistList : SearchStates()
}
