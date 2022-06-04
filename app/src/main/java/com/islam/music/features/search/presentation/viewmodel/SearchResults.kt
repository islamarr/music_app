package com.islam.music.features.search.presentation.viewmodel

import com.islam.music.common.Result
import com.islam.music.features.search.domain.entites.Artist

sealed class SearchResults : Result{
    object Loading : SearchResults()
    object UnExpectedError : SearchResults()
    object ArtistEmptyList : SearchResults()
    data class ArtistListLoaded(val artistList: List<Artist>) : SearchResults()
    data class Error(val reason: String) : SearchResults()
}
