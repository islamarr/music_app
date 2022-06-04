package com.islam.music.features.album_details.data.repositories

import com.islam.music.common.NetworkRemoteServiceCall
import com.islam.music.common.NetworkRemoteServiceCall2
import com.islam.music.common.NetworkResponse
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSource
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import javax.inject.Inject

class AlbumDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumDetailsRemoteDataSource,
    private val localDataSource: AlbumDetailsLocalDataSource
) : AlbumDetailsRepository {

    override suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): NetworkResponse<AlbumEntity> {
        return object : NetworkRemoteServiceCall2<AlbumEntity>(
            apiCall = { remoteDataSource.getAlbumDetails(artistName, albumName) },
            cacheCall = { localDataSource.getOneFavoriteAlbum(artistName, albumName) }
        ) {}.safeCall()
    }

    override suspend fun addToFavoriteList(album: AlbumEntity) {
        localDataSource.addToFavoriteList(album)
    }

    override suspend fun removeFromFavoriteList(album: AlbumEntity) {
        localDataSource.removeFromFavoriteList(album)
    }

}