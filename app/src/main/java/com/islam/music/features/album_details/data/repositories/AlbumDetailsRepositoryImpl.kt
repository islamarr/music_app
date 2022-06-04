package com.islam.music.features.album_details.data.repositories

import com.islam.music.common.Resource
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSource
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumDetailsRemoteDataSource,
    private val localDataSource: AlbumDetailsLocalDataSource
) : AlbumDetailsRepository {

    override suspend fun getAlbumDetails(
        artistName: String,
        albumName: String
    ): Flow<Resource<AlbumEntity>> = flow {
            try {
                val response = remoteDataSource.getAlbumDetails(artistName, albumName)
                localDataSource.addToFavoriteList(response)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                try {
                    val local = localDataSource.getOneFavoriteAlbum(artistName, albumName)
                    emit(Resource.Success(local))
                } catch (ex: Exception) {
                    emit(Resource.Error(ex))
                }
            }

    }

    override suspend fun addToFavoriteList(album: AlbumEntity) {
        localDataSource.addToFavoriteList(album)
    }

    override suspend fun removeFromFavoriteList(album: AlbumEntity) {
        localDataSource.removeFromFavoriteList(album)
    }

}