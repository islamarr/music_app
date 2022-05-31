package com.islam.music.features.top_albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.islam.music.R
import com.islam.music.common.view.BaseFragment
import com.islam.music.databinding.FragmentTopAlbumsBinding
import com.islam.music.features.main_screen.domain.entites.Album
import com.islam.music.features.main_screen.presentation.view.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopAlbumsFragment : BaseFragment<FragmentTopAlbumsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopAlbumsBinding
        get() = FragmentTopAlbumsBinding::inflate
    override var screenTitle = R.string.top_albums_screen_title

    private var list: MutableList<Album> = mutableListOf()

    private lateinit var albumsAdapter: AlbumsAdapter

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()

        for (i in 1..10) { //TODO remove after handle viewmodel
            list.add(Album(i, "top_album_$i"))
        }
        albumsAdapter.submitList(list)
    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            albumsAdapter = AlbumsAdapter()
            adapter = albumsAdapter
        }
    }

}