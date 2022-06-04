package com.islam.music.features.main_screen.presentation.view

import com.islam.music.common.BaseDiffCallback
import com.islam.music.features.top_albums.domain.entites.Album

class AlbumsDiffUtil : BaseDiffCallback<Album>() {

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }
}