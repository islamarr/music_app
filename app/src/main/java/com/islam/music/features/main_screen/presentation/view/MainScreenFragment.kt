package com.islam.music.features.main_screen.presentation.view

import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.islam.music.R
import com.islam.music.common.BaseFragment
import com.islam.music.databinding.FragmentMainScreenBinding
import com.islam.music.features.main_screen.domain.entites.Album
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate
    override var screenTitle = R.string.main_screen_title
    private var list: MutableList<Album> = mutableListOf()

    private lateinit var albumsAdapter: AlbumsAdapter

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()


        for (i in 1..100) {
            list.add(Album(i, "title_$i"))
        }
        albumsAdapter.submitList(list)

    }

    private fun initRecyclerView() {
        binding.list.apply {
            albumsAdapter = AlbumsAdapter()
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


}