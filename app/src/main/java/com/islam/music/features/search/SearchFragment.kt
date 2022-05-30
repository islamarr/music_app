package com.islam.music.features.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.islam.music.R
import com.islam.music.common.BaseFragment
import com.islam.music.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
    override var screenTitle = R.string.search_screen_title

    override fun setupOnViewCreated(view: View) {
        binding.testNav.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_topAlbumsFragment)
        }

    }

}