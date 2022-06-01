package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.data.remote.api.AlbumDetailsAPIService
import com.islam.music.features.album_details.domain.entites.AlbumDetailsResponse
import com.islam.music.features.search.data.remote.api.SearchAPIService
import retrofit2.Response
import javax.inject.Inject

class AlbumDetailsDataSourceImpl @Inject constructor(private val apiService: AlbumDetailsAPIService) :
    AlbumDetailsDataSource {
    override suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): NetworkResponse<Response<AlbumDetailsResponse>> {
        return safeApiCall {
            apiService.getAlbumDetails(artistName, albumName)
        }
    }
}