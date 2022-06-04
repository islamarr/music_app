package com.islam.music.features.album_details.domain.repositories

import com.islam.music.common.NetworkRemoteServiceCall
import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity

interface AlbumDetailsRepository {
    suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): NetworkResponse<AlbumEntity>

    suspend fun addToFavoriteList(album: AlbumEntity)
    suspend fun removeFromFavoriteList(album: AlbumEntity)
    //fun getFavoriteList(): MutableList<AlbumEntity>
    // fun getOneFavoriteAlbum(artistName: String, albumName: String): AlbumEntity
}