package com.islam.music.features.search.data.remote.datasource

import com.islam.music.features.search.data.remote.api.SearchAPIService
import com.islam.music.features.search.domain.entites.ArtistResponse
import javax.inject.Inject

class SearchArtistDataSourceImpl @Inject constructor(private val searchAPIService: SearchAPIService) :
    SearchArtistDataSource {
    override suspend fun searchArtist(query: String) :ArtistResponse {
        return searchAPIService.searchByArtist(query, 1) //TODO paging

    }
}