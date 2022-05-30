package com.islam.music.features.main_screen.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.music.databinding.OneItemAlbumBinding
import com.islam.music.features.main_screen.domain.entites.Album

class AlbumsAdapter :
    ListAdapter<Album, AlbumsAdapter.MyViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = OneItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }


    class MyViewHolder(private var binding: OneItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Album) {
            binding.title.text = item.title
            binding.dd.text = item.id.toString()
        }

    }


}