package com.islam.music.features.search.domain.repositories

import com.islam.music.common.NetworkResponse
import com.islam.music.features.search.domain.entites.ArtistResponse
import retrofit2.Response

interface SearchArtistRepository {
    suspend fun searchArtist(query: String) : NetworkResponse<Response<ArtistResponse>>
}