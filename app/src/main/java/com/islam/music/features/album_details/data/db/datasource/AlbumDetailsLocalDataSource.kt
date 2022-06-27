package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import kotlinx.coroutines.flow.Flow

interface AlbumDetailsLocalDataSource {
    suspend fun addToFavoriteList(album: AlbumEntity)
    suspend fun removeFromFavoriteList(album: AlbumEntity)
    suspend fun getFavoriteList(): Flow<List<AlbumEntity>>
    fun getOneFavoriteAlbum(albumParams: AlbumParams): AlbumEntity
}