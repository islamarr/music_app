package com.islam.music.features.main_screen.presentation.view

import android.util.Log
import android.view.*
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
import com.islam.music.databinding.FragmentMainScreenBinding
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsActions
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenActions
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenStates
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenViewModel
import com.islam.music.features.top_albums.presentation.view.TopAlbumsFragmentArgs
import com.islam.music.features.top_albums.presentation.view.TopAlbumsFragmentDirections
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsActions
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsStates
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>(), OnItemClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate
    override var screenTitle = R.string.main_screen_title

    private val viewModel: MainScreenViewModel by viewModels()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()
        startObserver()
        loadAlbumList()
    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            albumsAdapter = AlbumsAdapter(this@MainScreenFragment)
            adapter = albumsAdapter
        }
    }

    private fun loadAlbumList() {
        viewModel.dispatch(MainScreenActions.LoadAllSavedAlbums)
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

    private fun handleViewState(it: MainScreenStates) {
        when (it) {
            is MainScreenStates.InitialState -> Log.d("tag", "handleViewState: ")
            is MainScreenStates.Loading -> binding.container.loading.visible()
            is MainScreenStates.TopAlbumsListLoaded -> {
                showEmptyList(false)
                albumsAdapter.submitList(it.topAlbumsList)
            }
            is MainScreenStates.EmptyTopAlbumsList -> {
                showEmptyList(true)
                // binding.retryBtn.gone()
                binding.container.resultStatusText.text = getString(R.string.no_data_to_show)
            }
            is MainScreenStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.container.resultStatusText.text = getString(R.string.error_message)
            }
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

    override fun onClick(data: String, data2: String?) {
        data2?.let {
            findNavController()
                .navigate(
                    MainScreenFragmentDirections.actionMainScreenFragmentToAlbumDetailsFragment(
                        data2,
                        data
                    )
                )
        }
    }

}