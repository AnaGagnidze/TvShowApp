package com.example.moviesapp.ui.tvShowDetails

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapters.FiltersRecyclerViewAdapter
import com.example.moviesapp.adapters.tvShowsAdapter.TvShowsRecyclerViewAdapter
import com.example.moviesapp.base.BaseFragment
import com.example.moviesapp.databinding.TvShowDetailsFragmentBinding
import com.example.moviesapp.models.showDetailModels.ShowDetailResponseModel
import com.example.moviesapp.network.ResultControl
import com.example.moviesapp.utils.extensions.setImageUrl
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TvShowDetailsFragment :
    BaseFragment<TvShowDetailsFragmentBinding>(TvShowDetailsFragmentBinding::inflate) {

    private val showDetailsViewModel: TvShowDetailsViewModel by viewModel {
        parametersOf(TvShowDetailsFragmentArgs.fromBundle(requireArguments()).tvId)
    }

    private lateinit var similarShowsAdapter: TvShowsRecyclerViewAdapter
    private lateinit var genresAdapter: FiltersRecyclerViewAdapter


    override fun setUpFragment() {
        setUpSimilarShowsRecyclerView()
        observe()
    }

    private fun setUpSimilarShowsRecyclerView() {
        similarShowsAdapter = TvShowsRecyclerViewAdapter(true)
        binding.similarShowsRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.similarShowsRV.adapter = similarShowsAdapter
    }

    private fun observe() {
        showDetailsViewModel.getSimilarShows().observe(viewLifecycleOwner, {
            similarShowsAdapter.submitData(lifecycle, it)
        })

        showDetailsViewModel._showDetails.observe(viewLifecycleOwner, {
            when (it.status) {
                ResultControl.Status.SUCCESS -> {
                    handleData(it.data)
                }

                ResultControl.Status.ERROR -> {
                    Toast.makeText(requireActivity(), "${it.message}", Toast.LENGTH_SHORT).show()
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
}