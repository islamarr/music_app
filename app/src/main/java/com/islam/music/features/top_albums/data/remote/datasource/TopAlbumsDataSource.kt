package com.islam.music.features.top_albums.data.remote.datasource

import com.islam.music.common.NetworkRemoteServiceCall
import com.islam.music.common.NetworkResponse
import com.islam.music.features.search.domain.entites.ArtistResponse
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import retrofit2.Response

interface TopAlbumsDataSource : NetworkRemoteServiceCall {
    suspend fun getTopAlbums(artistName: String) : NetworkResponse<Response<TopAlbumsResponse>>
}