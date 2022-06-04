package com.islam.music.features.search.presentation.view

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.islam.music.R
import com.islam.music.common.OnItemClickListener
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.gone
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentSearchBinding
import com.islam.music.features.search.presentation.viewmodel.SearchActions
import com.islam.music.features.search.presentation.viewmodel.SearchStates
import com.islam.music.features.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchView.OnQueryTextListener ,
    OnItemClickListener {  // TODO set res file style ( strings - dimen - ... ) + make references for everything

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
    override var screenTitle = R.string.search_screen_title

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var artistsAdapter: ArtistsAdapter

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()
        startObserver()
    }

    private fun initRecyclerView() { //TODO handle pagination
        binding.container.list.apply {
            artistsAdapter = ArtistsAdapter(this@SearchFragment)
            adapter = artistsAdapter
        }
    }

    private fun searchArtistByName(query: String) {
        viewModel.dispatch(SearchActions.SearchArtistByName(query = query))
    }

    private fun startObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    handleViewState(it)
                }
            }
        }
    }

    private fun showEmptyList(show: Boolean) {
        binding.container.loading.gone()
        binding.container.resultStatusText.isVisible = show
        binding.container.list.isVisible = !show
    }

    private fun handleViewState(it: SearchStates) {
        when (it) {
            is SearchStates.InitialState -> Log.d("TAG", "InitialState: ")
            is SearchStates.Loading -> binding.container.loading.visible()
            is SearchStates.ArtistListLoaded -> {
                showEmptyList(false)
                artistsAdapter.submitList(it.artistList)
            }
            is SearchStates.EmptyArtistList -> {
                showEmptyList(true)
                // binding.retryBtn.gone()
                binding.container.resultStatusText.text = getString(R.string.no_data_to_show)
            }
            is SearchStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.container.resultStatusText.text = getString(R.string.error_message)
            }
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            searchArtistByName(it)
        }
        // findNavController().navigate(R.id.action_searchFragment_to_topAlbumsFragment)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            Log.d("TAG", "onQueryTextChange: $it")
        }
        return true
    }

    override fun onClick(data: String, data2: String?) {
        data.let {
            findNavController()
                .navigate(SearchFragmentDirections.actionSearchFragmentToTopAlbumsFragment(it))
        }
    }

}