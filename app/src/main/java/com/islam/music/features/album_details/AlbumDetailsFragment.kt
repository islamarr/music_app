package com.islam.music.features.album_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.islam.music.R
import com.islam.music.common.BaseFragment
import com.islam.music.databinding.FragmentAlbumDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : BaseFragment<FragmentAlbumDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAlbumDetailsBinding
        get() = FragmentAlbumDetailsBinding::inflate
    override var screenTitle = R.string.album_details_screen_title

    override fun setupOnViewCreated(view: View) {

    }

}