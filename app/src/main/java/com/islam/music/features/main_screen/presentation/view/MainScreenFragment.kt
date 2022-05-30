package com.islam.music.features.main_screen.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.islam.music.R
import com.islam.music.common.BaseFragment
import com.islam.music.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate

    override fun setupOnViewCreated(view: View) {
        binding.testNav.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_searchFragment)
        }
    }

}