package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.data.db.AlbumDao
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.main_screen.data.db.AlbumEntityToAlbumMapper
import com.islam.music.features.top_albums.domain.entites.Album
import javax.inject.Inject

class AlbumDetailsLocalDataSourceImpl @Inject constructor(private val albumDao: AlbumDao) :
    AlbumDetailsLocalDataSource {
    override suspend fun addToFavoriteList(album: AlbumEntity) {
        albumDao.addToFavoriteList(album)
    }

    override suspend fun removeFromFavoriteList(album: AlbumEntity) {
        albumDao.removeFromFavoriteList(album.albumName, album.artistName)
    }

    override fun getFavoriteList(): List<Album> {
        return albumDao.getFavoriteList().map {  AlbumEntityToAlbumMapper().map(it) }
    }

    override fun getOneFavoriteAlbum(
        artistName: String,
        albumName: String
    ): AlbumEntity {
        return albumDao.getOneFavoriteAlbum(artistName, albumName)
    }
}