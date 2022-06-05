package com.islam.music.features.search.presentation.view

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.islam.music.R
import com.islam.music.common.OnItemClickListener
import com.islam.music.common.gone
import com.islam.music.common.setKeyboardVisibility
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentSearchBinding
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.search.presentation.viewmodel.SearchActions
import com.islam.music.features.search.presentation.viewmodel.SearchStates
import com.islam.music.features.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchStates, SearchActions>(),
    SearchView.OnQueryTextListener,
    OnItemClickListener {  // TODO set res file style ( strings - dimen - ... ) + make references for everything

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
    override var screenTitle = R.string.search_screen_title

    override val viewModel: SearchViewModel by viewModels()
    private lateinit var artistsAdapter: ArtistsAdapter
    private var queryTyped = ""
    private var isReachBottom = false
    private var currentPage = 1
    private var artistList = mutableListOf<Artist>()

    override fun setupOnViewCreated() {
        initRecyclerView()
        setScrollListener()
    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            artistsAdapter = ArtistsAdapter(this@SearchFragment)
            adapter = artistsAdapter
        }
    }

    private fun searchArtistByName() {
        isReachBottom = false //TODO logic should be in usecase
        currentPage = 1
        artistList.clear()
        viewModel.dispatch(SearchActions.SearchArtistByName(query = queryTyped))
    }

    private fun loadMore() {
        viewModel.dispatch(SearchActions.LoadMore(query = queryTyped, page = ++currentPage))
    }

    private fun showEmptyList(show: Boolean) {
        binding.container.loading.gone()
        binding.container.resultStatusText.isVisible = show
        binding.container.list.isVisible = !show
    }

    override fun handleViewState(it: SearchStates) {
        when (it) {
            is SearchStates.InitialState -> Log.d("TAG", "InitialState: ")
            is SearchStates.Loading -> binding.container.loading.visible()
            is SearchStates.ArtistListLoaded -> {
                isReachBottom =
                    (it.result.startIndex + (currentPage * it.result.itemsPerPage.toInt())) >= it.result.totalResults
                showEmptyList(false)
                artistList.addAll(it.result.artists.artist)
                artistsAdapter.submitList(artistList.toList())
            }
            is SearchStates.EmptyArtistList -> {
                showEmptyList(true)
                // binding.retryBtn.gone()
                binding.container.resultStatusText.text = getString(R.string.no_data_to_show) //TODO review
            }
            is SearchStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.container.resultStatusText.text = getString(R.string.error_message)
            }
        }
    }

    private fun setScrollListener() {
        binding.container.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && !binding.container.loading.isVisible && !isReachBottom) {
                    loadMore()
                }
            }
        })
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        setKeyboardVisibility(isShow = false)
        query?.let {
            queryTyped = it
            searchArtistByName()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onClick(albumName: String?, artistName: String?) {
        setKeyboardVisibility(isShow = false)
        artistName?.let {
            findNavController()
                .navigate(SearchFragmentDirections.actionSearchFragmentToTopAlbumsFragment(it))
        }
    }

}