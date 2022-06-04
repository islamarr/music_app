package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import kotlinx.coroutines.flow.StateFlow

interface AlbumDetailsLocalDataSource {
    suspend fun addToFavoriteList(album: AlbumEntity)
    suspend fun removeFromFavoriteList(album: AlbumEntity)
    fun getFavoriteList(): MutableList<AlbumEntity>
    fun getOneFavoriteAlbum(artistName: String, albumName: String): AlbumEntity
}