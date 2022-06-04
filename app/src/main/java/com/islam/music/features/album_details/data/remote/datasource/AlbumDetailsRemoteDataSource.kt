package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.common.NetworkRemoteServiceCall
import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity

interface AlbumDetailsRemoteDataSource : NetworkRemoteServiceCall {
    suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): NetworkResponse<AlbumEntity>
}