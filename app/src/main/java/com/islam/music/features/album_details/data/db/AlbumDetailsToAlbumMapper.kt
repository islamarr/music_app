package com.islam.music.features.album_details.data.db

import com.islam.music.features.album_details.domain.entites.AlbumDetails
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import javax.inject.Inject

class AlbumDetailsToAlbumMapper @Inject constructor(){

    operator fun invoke(albumDetails: AlbumDetails): AlbumEntity {
        return AlbumEntity(
            artistName = albumDetails.artist,
            coverImageUrl = albumDetails.images[0].url,
            albumName = albumDetails.name,
            trackList = albumDetails.tracks.trackList
        )
    }

}