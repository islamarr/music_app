package com.islam.music.features.album_details.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.music.common.OnItemClickListener
import com.islam.music.databinding.OneGeneralItemBinding
import com.islam.music.features.album_details.domain.entites.Track

class TrackAdapter :
    ListAdapter<Track, TrackAdapter.MyViewHolder>(DiffCallback) { //TODO create base adapter

    companion object DiffCallback :
        DiffUtil.ItemCallback<Track>() { // TODO create class instead of this
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.attr.rank == newItem.attr.rank
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = OneGeneralItemBinding.inflate(
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


    inner class MyViewHolder(private var binding: OneGeneralItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Track) {
            binding.title.text = item.name
            binding.subtitle.text = item.duration.toString()
        }

    }


}