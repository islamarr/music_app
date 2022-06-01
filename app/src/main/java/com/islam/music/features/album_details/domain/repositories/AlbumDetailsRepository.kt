package com.islam.music.features.album_details.domain.repositories

import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.domain.entites.AlbumDetailsResponse
import retrofit2.Response

interface AlbumDetailsRepository {
    suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): NetworkResponse<Response<AlbumDetailsResponse>>
}