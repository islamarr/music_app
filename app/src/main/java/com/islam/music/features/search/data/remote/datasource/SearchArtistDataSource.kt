package com.islam.music.features.search.data.remote.datasource

import com.islam.music.common.NetworkRemoteServiceCall
import com.islam.music.common.NetworkResponse
import com.islam.music.features.search.domain.entites.ArtistResponse
import retrofit2.Response

interface SearchArtistDataSource : NetworkRemoteServiceCall {
    suspend fun searchArtist(query: String) : NetworkResponse<Response<ArtistResponse>>
}