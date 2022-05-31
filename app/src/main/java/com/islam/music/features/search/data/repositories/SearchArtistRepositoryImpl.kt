package com.islam.music.features.search.data.repositories

import com.islam.music.common.NetworkResponse
import com.islam.music.features.search.data.remote.datasource.SearchArtistDataSource
import com.islam.music.features.search.domain.entites.ArtistResponse
import com.islam.music.features.search.domain.repositories.SearchArtistRepository
import retrofit2.Response
import javax.inject.Inject

class SearchArtistRepositoryImpl @Inject constructor(private val dataSource: SearchArtistDataSource) :
    SearchArtistRepository {
    override suspend fun searchArtist(query: String): NetworkResponse<Response<ArtistResponse>> {
        return dataSource.searchArtist(query)
    }
}