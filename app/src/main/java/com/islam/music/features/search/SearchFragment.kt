package com.islam.music.features.search

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import com.islam.music.R
import com.islam.music.common.BaseFragment
import com.islam.music.databinding.FragmentSearchBinding
import com.islam.music.features.main_screen.domain.entites.Album
import com.islam.music.features.main_screen.presentation.view.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchView.OnQueryTextListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
    override var screenTitle = R.string.search_screen_title

    private var list: MutableList<Album> = mutableListOf()

    private lateinit var albumsAdapter: AlbumsAdapter

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()

        for (i in 1..100) { //TODO remove after handle viewmodel
            list.add(Album(i, "album_$i"))
        }
        albumsAdapter.submitList(list)

    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            albumsAdapter = AlbumsAdapter()
            adapter = albumsAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val searchManager = requireActivity().getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = menu.findItem(R.id.startSearch)?.actionView as SearchView
        setSearchView(searchView, searchManager)
    }

    private fun setSearchView(
        searchView: SearchView,
        searchManager: SearchManager
    ) {
        searchView.apply {
            queryHint = resources.getString(R.string.search_hint)
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setIconifiedByDefault(false)
            isSubmitButtonEnabled = true
            isIconified = false
            setOnQueryTextListener(this@SearchFragment)
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.startSearch -> {
                Log.d("TAG", "startSearch: ")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            Log.d("TAG", "onQueryTextSubmit: $it")
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            Log.d("TAG", "onQueryTextChange: $it")
        }
        return true
    }

}