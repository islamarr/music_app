package com.islam.music.features.search.data.remote.api

import com.islam.music.BuildConfig
import com.islam.music.features.search.domain.entites.ArtistResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPIService {

    @GET("?method=artist.search&format=json&api_key=${BuildConfig.API_KEY}")
    suspend fun searchByArtist(
        @Query("artist") artist: String,
        @Query("page") page: Int
    ): ArtistResponse

}