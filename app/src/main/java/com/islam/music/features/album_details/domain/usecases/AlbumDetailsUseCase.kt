package com.islam.music.features.album_details.domain.usecases

import com.islam.music.common.DataState
import com.islam.music.common.NetworkResponse
import com.islam.music.common.Resource
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsToAlbumMapper
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsActions
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsResults
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.Dispatcher
import javax.inject.Inject

@ViewModelScoped
class AlbumDetailsUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    suspend fun save(albumEntity: AlbumEntity)  {
        repository.addToFavoriteList(albumEntity)
    }
    suspend fun execute(
        artistName: String,//TODO add artistName + albumName in data class
        albumName: String
    ): Flow<Resource<AlbumEntity>> = flow {
        repository.getAlbumDetails(artistName, albumName).flowOn(Dispatchers.IO).collect {
            emit(it)
        }

            /*is NetworkResponse.Success -> {
                response.data?.body()?.let {
                    AlbumDetailsResults.RemoteAlbumDetails(AlbumDetailsToAlbumMapper().map(it.album)) //TODO inject mapper
                } ?: AlbumDetailsResults.UnExpectedError
            }
            is NetworkResponse.Failure -> {
                response.reason?.let {
                    AlbumDetailsResults.Error(response.reason, response.httpCode)
                } ?: AlbumDetailsResults.UnExpectedError
            }*/

    }

}