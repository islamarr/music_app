package com.islam.music.features.album_details.data.db.datasource

import android.util.Log
import com.islam.music.features.album_details.domain.entites.AlbumDetails
import com.islam.music.features.album_details.domain.entites.AlbumEntity

class AlbumDetailsToAlbumMapper {

    fun map(albumDetails: AlbumDetails): AlbumEntity {
        Log.d("zzzz", "map: ${albumDetails.artist}  ${albumDetails.tracks.trackList}")
        if (albumDetails.tracks.trackList.isNotEmpty()) Log.d("zzzz", "trackList:  ${albumDetails.tracks.trackList[0].name}")
        return AlbumEntity(
            artistName = albumDetails.artist,
            coverImageUrl = albumDetails.images[0].url,
            albumName = albumDetails.name,
            trackList = albumDetails.tracks.trackList
        )
    }

}