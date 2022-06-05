package com.islam.music.features.search.presentation.viewmodel

import com.islam.music.common.view.BaseViewModel
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchArtistUseCase) :
    BaseViewModel<SearchStates, SearchActions>(SearchStates.InitialState) {

    override fun handle(actions: SearchActions): Flow<SearchStates> = flow {
        emit(SearchStates.Loading)
        when (actions) {
            is SearchActions.SearchArtistByName -> {
                emit(useCase.execute(actions.query, 1))
            }
            is SearchActions.LoadMore -> emit(useCase.execute(actions.query, actions.page))
        }
    }

}