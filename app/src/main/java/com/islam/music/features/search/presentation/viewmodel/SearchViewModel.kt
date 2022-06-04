package com.islam.music.features.search.presentation.viewmodel

import com.islam.music.common.BaseViewModel
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchArtistUseCase) :
    BaseViewModel<SearchStates, SearchActions, SearchResults>(SearchStates.InitialState) {

    override fun reduce(result: SearchResults): SearchStates =
        when (result) {
            is SearchResults.UnExpectedError -> SearchStates.ShowErrorMessage()
            is SearchResults.Error -> SearchStates.ShowErrorMessage(result.reason)
            is SearchResults.ArtistListLoaded -> SearchStates.ArtistListLoaded(result.artistList)
            is SearchResults.ArtistEmptyList -> SearchStates.EmptyArtistList
            is SearchResults.Loading -> SearchStates.Loading
        }

    override fun handle(actions: SearchActions): Flow<SearchResults> = flow {
        when (actions) {
            is SearchActions.SearchArtistByName -> {
                emit(SearchResults.Loading)
                emit(useCase.execute(actions.query))
            }
        }
    }

}