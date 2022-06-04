package com.islam.music.features.search.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.islam.music.R
import com.islam.music.common.IMAGE_SIZE_MULTIPLIER
import com.islam.music.common.OnItemClickListener
import com.islam.music.databinding.OneGeneralItemBinding
import com.islam.music.features.search.domain.entites.Artist

class ArtistsAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Artist, ArtistsAdapter.MyViewHolder>(DiffCallback) { //TODO create base adapter


    companion object DiffCallback : DiffUtil.ItemCallback<Artist>() { // TODO create class instead of this
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
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

        fun bind(item: Artist) {
            binding.title.text = item.name
            loadImage(itemView.context, item.images[0].url)

            itemView.setOnClickListener {
                onItemClickListener.onClick(item.name)
            }
        }

        private fun loadImage(context: Context, url: String?) {
            Glide.with(context).load(url)
                // .placeholder(R.drawable.loading_img)
                .thumbnail(IMAGE_SIZE_MULTIPLIER)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.itemImage)
        }
    }


}