package com.islam.music.features.top_albums.data.remote.datasource

import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse

interface TopAlbumsDataSource  {
    suspend fun getTopAlbums(artistName: String) : TopAlbumsResponse
}