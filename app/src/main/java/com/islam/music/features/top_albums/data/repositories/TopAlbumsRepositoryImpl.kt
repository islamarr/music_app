package com.islam.music.features.top_albums.data.repositories

import com.islam.music.common.NetworkResponse
import com.islam.music.features.top_albums.data.remote.datasource.TopAlbumsDataSource
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import retrofit2.Response
import javax.inject.Inject

class TopAlbumsRepositoryImpl @Inject constructor(private val dataSource: TopAlbumsDataSource) : TopAlbumsRepository {
    override suspend fun getTopAlbums(artistName: String): NetworkResponse<Response<TopAlbumsResponse>> {
        return dataSource.getTopAlbums(artistName)
    }
}