package com.example.moviesapp.ui.tvShowDetails

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapters.FiltersRecyclerViewAdapter
import com.example.moviesapp.adapters.tvShowsAdapter.TvShowsRecyclerViewAdapter
import com.example.moviesapp.base.BaseFragment
import com.example.moviesapp.databinding.TvShowDetailsFragmentBinding
import com.example.moviesapp.models.showDetailModels.ShowDetailResponseModel
import com.example.moviesapp.network.ResultControl
import com.example.moviesapp.utils.extensions.setImageUrl
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TvShowDetailsFragment :
    BaseFragment<TvShowDetailsFragmentBinding>(TvShowDetailsFragmentBinding::inflate) {

    private val showDetailsViewModel: TvShowDetailsViewModel by viewModel {
        parametersOf(TvShowDetailsFragmentArgs.fromBundle(requireArguments()).tvId)
    }

    private var similarShowsAdapter: TvShowsRecyclerViewAdapter? = null
    private var genresAdapter: FiltersRecyclerViewAdapter? = null


    override fun setUpFragment() {
        setUpSimilarShowsRecyclerView()
        observe()
        setClickListeners()
    }

    private fun setUpSimilarShowsRecyclerView() {
        similarShowsAdapter = TvShowsRecyclerViewAdapter(true)
        binding.similarShowsRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.similarShowsRV.adapter = similarShowsAdapter
    }

    private fun setClickListeners() {
        binding.retryButton.setOnClickListener{
            observe()
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observe() {
        showDetailsViewModel.getSimilarShows().observe(viewLifecycleOwner, {
            similarShowsAdapter?.submitData(lifecycle, it)
        })

        showDetailsViewModel._showDetails.observe(viewLifecycleOwner, {
            when (it.status) {
                ResultControl.Status.SUCCESS -> {
                    handleData(it.data)
                    binding.wholePageItemsGr.isVisible = true
                    binding.progressBar.isVisible = it.refreshing
                }

                ResultControl.Status.ERROR -> {
                    binding.errorItemsGr.isVisible = true
                    binding.errorText.text = it.message
                    binding.progressBar.isVisible = it.refreshing
                }
                ResultControl.Status.LOADING -> {
                    binding.progressBar.isVisible = it.refreshing
                }
            }
        })
    }

    private fun handleData(data: ShowDetailResponseModel?) {
        with(binding) {
            ivShowPoster.setImageUrl(data?.generatePosterUrl())
            showTitleTv.text = data?.name
            yearTv.text = data?.firstAirDate
            setDirectors(data)
            showRatingNumTV.text = data?.voteAverage.toString()
            setGenres(data)
            showDescriptionTv.text = data?.overview
        }
    }

    private fun setGenres(data: ShowDetailResponseModel?) {
        val genres = mutableListOf<String>()
        for (i in data?.genres!!.iterator()) {
            genres.add(i.name)
        }
        genresAdapter = FiltersRecyclerViewAdapter(genres)
        binding.genresRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.genresRV.adapter = genresAdapter
    }

    private fun setDirectors(data: ShowDetailResponseModel?) {
        val directors = mutableListOf<String>()
        for (i in data?.createdBy!!.iterator()) {
            directors.add(i.name)
        }

        binding.directorTv.text = directors.joinToString(", ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        similarShowsAdapter = null
        genresAdapter = null
    }
}