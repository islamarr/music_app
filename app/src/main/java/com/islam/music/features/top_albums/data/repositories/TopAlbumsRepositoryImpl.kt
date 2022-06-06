package com.islam.music.features.top_albums.data.repositories

import com.islam.music.common.data.DataResponse
import com.islam.music.common.data.SafeServiceCall
import com.islam.music.features.top_albums.data.remote.datasource.TopAlbumsRemoteDataSource
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import javax.inject.Inject

class TopAlbumsRepositoryImpl @Inject constructor(private val dataSource: TopAlbumsRemoteDataSource) :
    TopAlbumsRepository {
    override suspend fun getTopAlbums(artistName: String): DataResponse<TopAlbumsResponse> {
        return object : SafeServiceCall<TopAlbumsResponse>(
            apiCall = { dataSource.getTopAlbums(artistName) },
        ) {}.safeCall()
    }
}