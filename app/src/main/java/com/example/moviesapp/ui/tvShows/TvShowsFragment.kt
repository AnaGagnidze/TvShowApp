package com.example.moviesapp.ui.tvShows

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
        observe("popular")
        setListeners()
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            observe("popular")
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
                0 -> observe(FiltersEnum.POPULAR.name)
                1 -> observe(FiltersEnum.TOP_RATED.name)
                2 -> observe(FiltersEnum.AIRING_TODAY.name)
                else -> observe(FiltersEnum.POPULAR.name)
            }
        }
    }

    private fun setUpTvShowsRecyclerView() {
        showsAdapter = TvShowsRecyclerViewAdapter()
        binding.showsRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.showsRV.adapter = showsAdapter
    }

    private fun observe(keyword: String) {
        tvShowsViewModel.getTvShows(keyword).observe(viewLifecycleOwner, {
            showsAdapter.submitData(lifecycle, it)
        })
        binding.swipeRefresh.isRefreshing = false
    }


}