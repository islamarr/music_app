package com.islam.music.features.album_details.presentation.view

import com.islam.music.common.BaseDiffCallback
import com.islam.music.features.album_details.domain.entites.Track

class TrackerDiffUtil : BaseDiffCallback<Track>() {

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.attr.rank == newItem.attr.rank
    }
}