package com.islam.music.features.album_details.data.repositories

import com.islam.music.common.data.DataResponse
import com.islam.music.common.data.SafeServiceCall
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSource
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.top_albums.domain.entites.Album
import javax.inject.Inject

class AlbumDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumDetailsRemoteDataSource,
    private val localDataSource: AlbumDetailsLocalDataSource
) : AlbumDetailsRepository {

    override suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): DataResponse<AlbumEntity> {
        return object : SafeServiceCall<AlbumEntity>(
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

    override suspend fun getFavoriteList(): DataResponse<List<Album>> {
        return object : SafeServiceCall<List<Album>>(
            cacheCall = { localDataSource.getFavoriteList() }
        ) {}.safeCall()
    }

    override suspend fun getOneFavoriteAlbum(
        artistName: String,
        albumName: String
    ): DataResponse<AlbumEntity> {
        return object : SafeServiceCall<AlbumEntity>(
            cacheCall = { localDataSource.getOneFavoriteAlbum(artistName, albumName) }
        ) {}.safeCall()
    }

}