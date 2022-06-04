package com.islam.music.features.album_details.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.islam.music.common.gone
import com.islam.music.common.view.BaseListAdapter
import com.islam.music.databinding.OneGeneralItemBinding
import com.islam.music.features.album_details.domain.entites.Track

class TrackAdapter :
    BaseListAdapter<Track>(TrackerDiffUtil()) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return OneGeneralItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(viewBinding: ViewBinding, item: Track, itemView: View) {
        val binding = viewBinding as OneGeneralItemBinding
        binding.title.text = item.name
        binding.subtitle.text = item.duration.toString()
        binding.nextBtn.gone()
        binding.itemImage.gone()
        val trackTime = String.format("%02d:%02d", item.duration / 60, item.duration % 60)
        binding.subtitle.text = trackTime
    }

}