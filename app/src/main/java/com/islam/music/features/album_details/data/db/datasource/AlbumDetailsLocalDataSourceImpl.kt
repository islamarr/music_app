package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AlbumDetailsLocalDataSourceImpl @Inject constructor(private val albumDao: AlbumDao) :
    AlbumDetailsLocalDataSource {
    override suspend fun addToFavoriteList(album: AlbumEntity) {
        albumDao.addToFavoriteList(album)
    }

    override suspend fun removeFromFavoriteList(album: AlbumEntity) {
        albumDao.removeFromFavoriteList(album)
    }

    override fun getFavoriteList(): MutableList<AlbumEntity> {
        return albumDao.getFavoriteList()
    }

    override fun getOneFavoriteAlbum(
        artistName: String,
        albumName: String
    ): AlbumEntity {
        return albumDao.getOneFavoriteAlbum(artistName, albumName)
    }
}