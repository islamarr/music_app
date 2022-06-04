package com.islam.music.features.search.presentation.view

import com.islam.music.common.BaseDiffCallback
import com.islam.music.features.search.domain.entites.Artist

class ArtistDiffUtil : BaseDiffCallback<Artist>() {
    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.id == newItem.id
    }
}