package com.islam.music.features.album_details.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.islam.music.R
import com.islam.music.common.IMAGE_SIZE_MULTIPLIER
import com.islam.music.common.gone
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentAlbumDetailsBinding
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsActions
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsStates
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment :
    BaseFragment<FragmentAlbumDetailsBinding, AlbumDetailsStates, AlbumDetailsActions>() { //TODO add back btn in toolbar

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAlbumDetailsBinding
        get() = FragmentAlbumDetailsBinding::inflate
    override var screenTitle = R.string.album_details_screen_title // TODO edit title

    private lateinit var trackAdapter: TrackAdapter
    override val viewModel: AlbumDetailsViewModel by viewModels()
    private val args: AlbumDetailsFragmentArgs by navArgs()
    private var albumEntity = AlbumEntity()
    private var isFavorite: Boolean = false

    override fun setupOnViewCreated() {
        initRecyclerView()
        setArgumentsData()
        binding.addToFavorite.setOnClickListener {
            isFavorite = !isFavorite
            viewModel.dispatch(AlbumDetailsActions.SetFavoriteAction(isFavorite, albumEntity))
        }
    }

    private fun setArgumentsData() {
        binding.albumName.text = args.albumName
        binding.albumArtistName.text = args.artistName
        loadImage(args.imageUrl)
    }

    private fun initRecyclerView() {
        binding.albumTrackList.apply {
            trackAdapter = TrackAdapter()
            adapter = trackAdapter
        }
    }

    private fun loadImage(url: String?) {
        Glide.with(requireContext()).load(url)
            .placeholder(R.drawable.ic_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.albumCoverImage)
    }

    private fun loadAlbumDetails(artistName: String, albumName: String) {
        viewModel.dispatch(AlbumDetailsActions.AlbumDetailsAction(artistName, albumName))
    }

    private fun showEmptyList(show: Boolean) {
        binding.loading.gone()
        binding.resultStatusText.isVisible = show
        binding.albumTrackList.isVisible = !show
    }

    override fun handleViewState(it: AlbumDetailsStates) {
        when (it) {
            is AlbumDetailsStates.InitialState -> loadAlbumDetails(args.artistName, args.albumName)
            is AlbumDetailsStates.Loading -> binding.loading.visible()
            is AlbumDetailsStates.AlbumDetailsData -> {
                showEmptyList(it.isTrackListEmpty)
                trackAdapter.submitList(it.albumDetails.trackList)
                albumEntity = it.albumDetails
            }
            is AlbumDetailsStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.resultStatusText.text = getString(R.string.error_message)
            }
            is AlbumDetailsStates.SavedState -> {
                binding.addToFavorite.visible()
                isFavorite = it.isSaved
                binding.addToFavorite.isChecked = isFavorite
            }
        }
    }

}