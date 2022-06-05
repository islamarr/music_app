package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.features.album_details.data.db.AlbumDetailsToAlbumMapper
import com.islam.music.features.album_details.data.remote.api.AlbumDetailsAPIService
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import javax.inject.Inject

class AlbumDetailsRemoteDataSourceImpl @Inject constructor(
    private val apiService: AlbumDetailsAPIService,
    private val albumDetailsToAlbumMapper: AlbumDetailsToAlbumMapper
) :
    AlbumDetailsRemoteDataSource {

    override suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): AlbumEntity {
        return albumDetailsToAlbumMapper.invoke(
            apiService.getAlbumDetails(
                artistName,
                albumName
            ).album
        )
    }
}