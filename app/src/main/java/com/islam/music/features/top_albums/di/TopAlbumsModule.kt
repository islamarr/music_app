package com.islam.music.features.top_albums.di

import com.islam.music.features.top_albums.data.remote.datasource.TopAlbumsDataSource
import com.islam.music.features.top_albums.data.remote.datasource.TopAlbumsDataSourceImpl
import com.islam.music.features.top_albums.data.repositories.TopAlbumsRepositoryImpl
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class TopAlbumsModule {

    @Binds
    abstract fun bindTopAlbumsRepository(repository: TopAlbumsRepositoryImpl): TopAlbumsRepository

    @Binds
    abstract fun bindTopAlbumsRemoteDataSource(dataSource: TopAlbumsDataSourceImpl): TopAlbumsDataSource

}