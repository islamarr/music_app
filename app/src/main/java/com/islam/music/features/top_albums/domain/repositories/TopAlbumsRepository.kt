package com.islam.music.features.top_albums.domain.repositories

import com.islam.music.common.NetworkResponse
import com.islam.music.features.search.domain.entites.ArtistResponse
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import retrofit2.Response

interface TopAlbumsRepository {
    suspend fun getTopAlbums(artistName: String) : NetworkResponse<Response<TopAlbumsResponse>>
}