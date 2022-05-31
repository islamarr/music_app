package com.islam.music.features.search.presentation.viewmodel

import com.islam.music.features.search.domain.entites.Artist

sealed class SearchResults {
    object Loading : SearchResults()
    object UnExpectedError : SearchResults()
    object ArtistEmptyList : SearchResults()
    data class ArtistListLoaded(val carImageURLList: List<Artist>) : SearchResults()
    data class Error(val reason: String, val errorCode: Int) : SearchResults()
}
