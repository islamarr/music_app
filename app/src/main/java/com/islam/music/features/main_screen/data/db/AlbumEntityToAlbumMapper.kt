package com.islam.music.features.main_screen.data.db

import android.util.Log
import com.islam.music.features.album_details.domain.entites.AlbumDetails
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.top_albums.domain.entites.Album
import com.islam.music.features.top_albums.domain.entites.Artist
import com.islam.music.features.top_albums.domain.entites.Image

class AlbumEntityToAlbumMapper {

    fun map(albumEntity: AlbumEntity): Album {
        return Album(
            name = albumEntity.albumName!!,
            images = listOf(Image("", albumEntity.coverImageUrl!!)),
            id = albumEntity.id.toString(),
            artist = Artist(name = albumEntity.artistName!!)
        )
    }

}