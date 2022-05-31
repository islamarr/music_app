package com.islam.music.features.album_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.islam.music.R
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.IMAGE_SIZE_MULTIPLIER
import com.islam.music.databinding.FragmentAlbumDetailsBinding
import com.islam.music.features.main_screen.domain.entites.Album
import com.islam.music.features.main_screen.presentation.view.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : BaseFragment<FragmentAlbumDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAlbumDetailsBinding
        get() = FragmentAlbumDetailsBinding::inflate
    override var screenTitle = R.string.album_details_screen_title

    private var list: MutableList<Album> = mutableListOf()

    private lateinit var albumsAdapter: AlbumsAdapter

    override fun setupOnViewCreated(view: View) {
        bindData()

        for (i in 1..100) { //TODO remove after handle viewmodel
            list.add(Album(i, "track_$i"))
        }
        albumsAdapter.submitList(list)
    }

    private fun bindData() {
        initRecyclerView()
        loadImage("https://i.picsum.photos/id/859/200/200.jpg?hmac=vEU-8IgIt_Q7UmUqqkedPnQX0g_C-6w4WPB2VHTzfgg")
        binding.albumName.text = "AA Album"
        binding.albumArtistName.text = "BB Artist"
    }

    private fun initRecyclerView() {
        binding.albumTrackList.apply {
            albumsAdapter = AlbumsAdapter()
            adapter = albumsAdapter
        }
    }

    private fun loadImage(url: String?) {
        Glide.with(requireContext()).load(url)
            // .placeholder(R.drawable.loading_img)
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .into(binding.albumCoverImage)
    }

}