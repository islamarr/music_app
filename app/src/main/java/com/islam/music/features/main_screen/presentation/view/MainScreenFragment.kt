package com.islam.music.features.main_screen.presentation.view

import android.view.*
import androidx.navigation.fragment.findNavController
import com.islam.music.R
import com.islam.music.common.OnItemClickListener
import com.islam.music.common.view.BaseFragment
import com.islam.music.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>(), OnItemClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate
    override var screenTitle = R.string.main_screen_title

    private lateinit var albumsAdapter: AlbumsAdapter

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()


    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            albumsAdapter = AlbumsAdapter(this@MainScreenFragment)
            adapter = albumsAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.startSearch -> {
                findNavController().navigate(R.id.action_mainScreenFragment_to_searchFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(data: String?) {
        TODO("Not yet implemented")
    }


}