package com.islam.music.features.main_screen.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.islam.music.common.IMAGE_SIZE_MULTIPLIER
import com.islam.music.common.OnItemClickListener
import com.islam.music.common.view.BaseListAdapter
import com.islam.music.databinding.OneGeneralItemBinding
import com.islam.music.features.top_albums.domain.entites.Album

class AlbumsAdapter(private val onItemClickListener: OnItemClickListener) :
    BaseListAdapter<Album>(AlbumsDiffUtil()) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return OneGeneralItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(viewBinding: ViewBinding, item: Album, itemView: View) {
        val binding = viewBinding as OneGeneralItemBinding
        binding.title.text = item.name
        loadImage(itemView.context, item.images[0].url, binding)

        itemView.setOnClickListener {
            onItemClickListener.onClick(albumName = item.name, artistName = item.artist.name)
        }
    }

    private fun loadImage(context: Context, url: String, binding: OneGeneralItemBinding) {
        Glide.with(context).load(url)
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.itemImage)
    }

}