package com.islam.music.features.album_details.domain.repositories

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import kotlinx.coroutines.flow.Flow

interface AlbumDetailsRepository {
    suspend fun getAlbumDetails(albumParams: AlbumParams): DataResponse<AlbumEntity>
    suspend fun addToFavoriteList(album: AlbumEntity)
    suspend fun removeFromFavoriteList(album: AlbumEntity)
    suspend fun getFavoriteList(): DataResponse<Flow<List<AlbumEntity>>>
    suspend fun getOneFavoriteAlbum(albumParams: AlbumParams): DataResponse<AlbumEntity>
}