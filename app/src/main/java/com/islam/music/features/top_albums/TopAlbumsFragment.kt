package com.islam.music.features.top_albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.islam.music.R
import com.islam.music.common.BaseFragment
import com.islam.music.databinding.FragmentTopAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopAlbumsFragment : BaseFragment<FragmentTopAlbumsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopAlbumsBinding
        get() = FragmentTopAlbumsBinding::inflate
    override var screenTitle = R.string.top_albums_screen_title

    override fun setupOnViewCreated(view: View) {

    }

}