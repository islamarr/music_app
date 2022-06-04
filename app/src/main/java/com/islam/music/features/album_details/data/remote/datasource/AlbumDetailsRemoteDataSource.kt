package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.features.album_details.domain.entites.AlbumEntity

interface AlbumDetailsRemoteDataSource {
    suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): AlbumEntity
}