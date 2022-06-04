package com.islam.music.features.top_albums.data.repositories

import com.islam.music.common.DataResponse
import com.islam.music.common.SafeServiceCall
import com.islam.music.features.top_albums.data.remote.datasource.TopAlbumsDataSource
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import javax.inject.Inject

class TopAlbumsRepositoryImpl @Inject constructor(private val dataSource: TopAlbumsDataSource) :
    TopAlbumsRepository {
    override suspend fun getTopAlbums(artistName: String): DataResponse<TopAlbumsResponse> {
        return object : SafeServiceCall<TopAlbumsResponse>(
            apiCall = { dataSource.getTopAlbums(artistName) },
        ) {}.safeCall()
    }
}