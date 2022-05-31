package com.islam.music.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchArtistUseCase) : ViewModel() {
    //TODO create base ViewModel

    private val _state = MutableStateFlow<SearchStates>(SearchStates.InitialState)
    val state: StateFlow<SearchStates>
        get() = _state.asStateFlow()

    fun dispatch(action: SearchActions) {
        handle(action).map { result ->
            reduce(result)
        }.onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }

    private suspend fun onViewState(state: SearchStates) {
        _state.emit(state)
    }

    fun reduce(result: SearchResults): SearchStates =
        when (result) {
            is SearchResults.UnExpectedError -> SearchStates.ShowErrorMessage()
            is SearchResults.Error -> SearchStates.ShowErrorMessage(result.reason, result.errorCode)
            is SearchResults.ArtistListLoaded -> SearchStates.ArtistListLoaded(result.artistList)
            is SearchResults.ArtistEmptyList -> SearchStates.EmptyArtistList
            is SearchResults.Loading -> SearchStates.Loading
        }

    fun handle(actions: SearchActions): Flow<SearchResults> = flow {
        when (actions) {
            is SearchActions.SearchArtistByName -> {
                emit(SearchResults.Loading)
                emit(useCase.execute(actions.query))
            }
        }
    }

}