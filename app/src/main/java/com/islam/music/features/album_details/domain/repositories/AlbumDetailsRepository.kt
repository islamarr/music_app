package com.islam.music.features.album_details.domain.repositories

import com.islam.music.common.NetworkRemoteServiceCall
import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.top_albums.domain.entites.Album

interface AlbumDetailsRepository {
    suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): NetworkResponse<AlbumEntity>

    suspend fun addToFavoriteList(album: AlbumEntity)
    suspend fun removeFromFavoriteList(album: AlbumEntity)
    suspend fun getFavoriteList(): NetworkResponse<List<Album>>
}