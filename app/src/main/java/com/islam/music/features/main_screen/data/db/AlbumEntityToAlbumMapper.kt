package com.islam.music.features.main_screen.data.db

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.top_albums.domain.entites.Album
import com.islam.music.features.top_albums.domain.entites.Artist
import com.islam.music.features.top_albums.domain.entites.Image
import javax.inject.Inject

class AlbumEntityToAlbumMapper @Inject constructor() {

    operator fun invoke(albumEntity: AlbumEntity): Album {
        return Album(
            albumName = albumEntity.albumName!!,
            images = listOf(Image("", albumEntity.coverImageUrl!!)),
            id = albumEntity.id.toString(),
            artist = Artist(name = albumEntity.artistName!!)
        )
    }

}