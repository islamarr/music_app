package com.islam.music.features.album_details.data.db

import androidx.room.*
import com.islam.music.features.album_details.domain.entites.AlbumEntity


@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavoriteList(album: AlbumEntity): Long

    @Delete
    suspend fun removeFromFavoriteList(album: AlbumEntity)

    @Query("SELECT * FROM album")
    fun getFavoriteList(): List<AlbumEntity>

    @Query("SELECT * FROM album WHERE artistName = :artistName and albumName = :albumName")
    fun getOneFavoriteAlbum(artistName: String, albumName: String): AlbumEntity

}