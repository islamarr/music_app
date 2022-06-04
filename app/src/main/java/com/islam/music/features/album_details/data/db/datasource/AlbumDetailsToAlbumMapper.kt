package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.domain.entites.AlbumDetails
import com.islam.music.features.album_details.domain.entites.AlbumEntity

class AlbumDetailsToAlbumMapper {

    fun map(albumDetails: AlbumDetails): AlbumEntity {
        return AlbumEntity(
            artistName = albumDetails.artist,
            coverImageUrl = albumDetails.images[0].url,
            albumName = albumDetails.name,
            trackList = albumDetails.tracks.trackList
        )
    }

}