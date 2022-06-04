package com.islam.music.features.album_details.domain.repositories

import com.islam.music.common.DataState
import com.islam.music.common.NetworkResponse
import com.islam.music.common.Resource
import com.islam.music.features.album_details.domain.entites.AlbumDetailsResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface AlbumDetailsRepository {
    suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): Flow<Resource<AlbumEntity>>

    suspend fun addToFavoriteList(album: AlbumEntity)
    suspend fun removeFromFavoriteList(album: AlbumEntity)
    //fun getFavoriteList(): MutableList<AlbumEntity>
   // fun getOneFavoriteAlbum(artistName: String, albumName: String): AlbumEntity
}