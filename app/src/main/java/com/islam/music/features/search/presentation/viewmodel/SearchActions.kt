package com.islam.music.features.search.presentation.viewmodel

sealed class SearchActions {
    data class SearchArtistByName(val query: String) : SearchActions()
}
