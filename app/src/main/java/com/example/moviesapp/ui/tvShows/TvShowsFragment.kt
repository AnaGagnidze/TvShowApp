package com.example.moviesapp.ui.tvShows

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapters.FiltersRecyclerViewAdapter
import com.example.moviesapp.adapters.tvShowsAdapter.TvShowsRecyclerViewAdapter
import com.example.moviesapp.base.BaseFragment
import com.example.moviesapp.databinding.TvShowsFragmentBinding
import com.example.moviesapp.utils.FiltersEnum
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>(TvShowsFragmentBinding::inflate) {

    private val tvShowsViewModel: TvShowsViewModel by viewModel()

    private lateinit var filtersAdapter: FiltersRecyclerViewAdapter
    private lateinit var showsAdapter: TvShowsRecyclerViewAdapter

    override fun setUpFragment() {
        setUpFiltersRecyclerView()
        setUpTvShowsRecyclerView()
        observe(FiltersEnum.POPULAR.filterName)
        setListeners()
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            when (filtersAdapter.currentFilterPosition) {
                0 -> observe(FiltersEnum.POPULAR.filterName)
                1 -> observe(FiltersEnum.TOP_RATED.filterName)
                2 -> observe(FiltersEnum.AIRING_TODAY.filterName)
                else -> observe(FiltersEnum.POPULAR.filterName)
            }
        }
    }

    private fun setUpFiltersRecyclerView() {
        val filters = mutableListOf(
            getString(R.string.popular_filter_icon_text),
            getString(R.string.top_rated_filter_icon_text),
            getString(R.string.Airing_today_filter_icon_text)
        )
        filtersAdapter = FiltersRecyclerViewAdapter(filters)
        binding.filtersRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.filtersRV.adapter = filtersAdapter
        filtersAdapter.onDrawableClick = { position ->
            when (position) {
                0 -> observe(FiltersEnum.POPULAR.filterName)
                1 -> observe(FiltersEnum.TOP_RATED.filterName)
                2 -> observe(FiltersEnum.AIRING_TODAY.filterName)
                else -> observe(FiltersEnum.POPULAR.filterName)
            }
        }
    }

    private fun setUpTvShowsRecyclerView() {
        showsAdapter = TvShowsRecyclerViewAdapter(false)
        binding.showsRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.showsRV.adapter = showsAdapter
        showsAdapter.onDrawableClick = {
            findNavController().navigate(
                TvShowsFragmentDirections.actionTvShowsFragmentToTvShowDetailsFragment(
                    it.toString()
                )
            )
        }
    }

    private fun observe(keyword: String) {
        tvShowsViewModel.getTvShows(keyword).observe(viewLifecycleOwner, {
            showsAdapter.submitData(lifecycle, it)
        })
        binding.swipeRefresh.isRefreshing = false
    }


}