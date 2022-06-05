package com.islam.music.features.top_albums.data.remote.api

import com.islam.music.BuildConfig
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TopAlbumsAPIService { //TODO remove and add format=json&api_key=${BuildConfig.API_KEY} to interceptor

    @GET("?method=artist.gettopalbums&format=json&api_key=${BuildConfig.API_KEY}")
    suspend fun getTopAlbums(@Query("artist") artistName: String): TopAlbumsResponse

}