package com.islam.music.features.search.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.search.domain.repositories.SearchArtistRepository
import com.islam.music.features.search.presentation.viewmodel.SearchStates
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchArtistUseCase @Inject constructor(private val repository: SearchArtistRepository) {

    suspend fun execute(query: String): SearchStates {
        return when (val response = repository.searchArtist(query)) {
            is DataResponse.Success -> {
                response.data?.let {
                    if (it.results.artists.artist.isEmpty()) SearchStates.EmptyArtistList else
                        SearchStates.ArtistListLoaded(it.results.artists.artist)
                } ?: SearchStates.ShowErrorMessage()
            }
            is DataResponse.Failure -> {
                response.reason?.let {
                    SearchStates.ShowErrorMessage(response.reason)
                } ?: SearchStates.ShowErrorMessage()
            }
        }
    }

}