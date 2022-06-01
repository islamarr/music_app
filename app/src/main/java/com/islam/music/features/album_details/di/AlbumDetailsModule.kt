package com.islam.music.features.album_details.di

import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsDataSourceImpl
import com.islam.music.features.album_details.data.repositories.AlbumDetailsRepositoryImpl
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class AlbumDetailsModule {

    @Binds
    abstract fun bindSearchArtistRepository(repository: AlbumDetailsRepositoryImpl): AlbumDetailsRepository

    @Binds
    abstract fun bindSearchArtistRemoteDataSource(dataSource: AlbumDetailsDataSourceImpl): AlbumDetailsDataSource

}