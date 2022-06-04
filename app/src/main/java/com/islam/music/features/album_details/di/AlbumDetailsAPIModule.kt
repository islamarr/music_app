package com.islam.music.features.album_details.di

import com.islam.music.features.album_details.data.remote.api.AlbumDetailsAPIService
import com.islam.music.features.top_albums.data.remote.api.TopAlbumsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class) //TODO Singleton better?
class AlbumDetailsAPIModule {

    @Provides
    fun provideAPIService(retrofit: Retrofit): AlbumDetailsAPIService {
        return retrofit.create(AlbumDetailsAPIService::class.java)
    }

}