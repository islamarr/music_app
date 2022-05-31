package com.islam.music.features.top_albums.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.islam.music.R
import com.islam.music.common.OnItemClickListener
import com.islam.music.common.gone
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentTopAlbumsBinding
import com.islam.music.features.main_screen.presentation.view.AlbumsAdapter
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsActions
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsStates
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopAlbumsFragment : BaseFragment<FragmentTopAlbumsBinding>(), OnItemClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopAlbumsBinding
        get() = FragmentTopAlbumsBinding::inflate
    override var screenTitle = R.string.top_albums_screen_title

    private val viewModel: TopAlbumsViewModel by viewModels()
    private lateinit var albumsAdapter: AlbumsAdapter
    private val args: TopAlbumsFragmentArgs by navArgs()

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()
        startObserver()
        updateTitle()
    }

    private fun updateTitle() { //TODO add name + title
        screenTitle = R.string.top_albums_screen_title
        setToolbarTitle()
    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            albumsAdapter = AlbumsAdapter(this@TopAlbumsFragment)
            adapter = albumsAdapter
        }
    }

    private fun loadTopAlbums(artistName: String) {
        viewModel.dispatch(TopAlbumsActions.LoadAllAlbums(artistName = artistName))
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

    private fun handleViewState(it: TopAlbumsStates) {
        when (it) {
            is TopAlbumsStates.InitialState -> loadTopAlbums(args.artistName)
            is TopAlbumsStates.Loading -> binding.container.loading.visible()
            is TopAlbumsStates.TopAlbumsListLoaded -> {
                showEmptyList(false)
                albumsAdapter.submitList(it.topAlbumsList)
            }
            is TopAlbumsStates.EmptyTopAlbumsList -> {
                showEmptyList(true)
                // binding.retryBtn.gone()
                binding.container.resultStatusText.text = getString(R.string.no_data_to_show)
            }
            is TopAlbumsStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.container.resultStatusText.text = getString(R.string.error_message)
            }
        }
    }

    override fun onClick(data: String?) {
        data?.let {
            findNavController()
                .navigate(R.id.action_topAlbumsFragment_to_albumDetailsFragment)
        }
    }

}