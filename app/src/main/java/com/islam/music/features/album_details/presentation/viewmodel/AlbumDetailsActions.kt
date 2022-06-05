package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.common.Action
import com.islam.music.features.album_details.domain.entites.AlbumEntity

sealed class AlbumDetailsActions : Action {
    data class AlbumDetailsAction(val artistName: String, val albumName: String) :
        AlbumDetailsActions()

    data class SetFavoriteAction(val isAdd: Boolean, val albumEntity: AlbumEntity) :
        AlbumDetailsActions()
}
