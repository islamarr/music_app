package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.data.db.AlbumDao
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumDetailsLocalDataSourceImpl @Inject constructor(
    private val albumDao: AlbumDao
) :
    AlbumDetailsLocalDataSource {
    override suspend fun addToFavoriteList(album: AlbumEntity) {
        albumDao.addToFavoriteList(album)
    }

    override suspend fun removeFromFavoriteList(album: AlbumEntity) {
        albumDao.removeFromFavoriteList(album.albumName, album.artistName)
    }

    override suspend fun getFavoriteList(): Flow<List<AlbumEntity>> {
        return albumDao.getFavoriteList()
    }

    override fun getOneFavoriteAlbum(
        albumParams: AlbumParams
    ): AlbumEntity {
        return albumDao.getOneFavoriteAlbum(albumParams.artistName, albumParams.albumName)
    }
}