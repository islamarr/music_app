package com.islam.music.features.search.presentation.viewmodel

import com.islam.music.common.Action

sealed class SearchActions : Action{
    data class SearchArtistByName(val query: String) : SearchActions()
    data class LoadMore(val query: String, val page: Int) : SearchActions()
}
