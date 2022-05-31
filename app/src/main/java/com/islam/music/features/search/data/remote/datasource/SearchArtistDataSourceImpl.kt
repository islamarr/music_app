package com.islam.music.features.search.data.remote.datasource

import com.islam.music.common.NetworkResponse
import com.islam.music.features.search.data.remote.api.SearchAPIService
import com.islam.music.features.search.domain.entites.ArtistResponse
import retrofit2.Response
import javax.inject.Inject

class SearchArtistDataSourceImpl @Inject constructor(private val searchAPIService: SearchAPIService) :
    SearchArtistDataSource {
    override suspend fun searchArtist(query: String) : NetworkResponse<Response<ArtistResponse>> {
        return safeApiCall {
             searchAPIService.searchByArtist(query, 1)
        }
    }
}