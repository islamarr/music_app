package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsToAlbumMapper
import com.islam.music.features.album_details.data.remote.api.AlbumDetailsAPIService
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import javax.inject.Inject

class AlbumDetailsRemoteDataSourceImpl @Inject constructor(private val apiService: AlbumDetailsAPIService) :
    AlbumDetailsRemoteDataSource {

    override suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): AlbumEntity {

           return AlbumDetailsToAlbumMapper().map(
                apiService.getAlbumDetails(
                    artistName,
                    albumName
                ).album
            ) //TODO change map parameter

    }
}