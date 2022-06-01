package com.islam.music.features.album_details.data.repositories

import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsDataSource
import com.islam.music.features.album_details.domain.entites.AlbumDetailsResponse
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import retrofit2.Response
import javax.inject.Inject

class AlbumDetailsRepositoryImpl @Inject constructor(private val dataSource: AlbumDetailsDataSource) :
    AlbumDetailsRepository {
    override suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): NetworkResponse<Response<AlbumDetailsResponse>> {
        return dataSource.getAlbumDetails(artistName, albumName)
    }
}