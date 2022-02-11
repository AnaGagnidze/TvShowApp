package com.example.moviesapp.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapters.FiltersRecyclerViewAdapter
import com.example.moviesapp.adapters.tvShowsAdapter.TvShowsRecyclerViewAdapter
import com.example.moviesapp.base.BaseFragment
import com.example.moviesapp.databinding.TvShowsFragmentBinding
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>(TvShowsFragmentBinding::inflate) {

    private val tvShowsViewModel: TvShowsViewModel by viewModel()

    private lateinit var filtersAdapter: FiltersRecyclerViewAdapter
    private lateinit var showsAdapter: TvShowsRecyclerViewAdapter

    override fun setUpFragment() {
        setUpFiltersRecyclerView()
        setUpTvShowsRecyclerView()
        observe()
        setListeners()
    }

    private fun setListeners(){
        binding.swipeRefresh.setOnRefreshListener {
            observe()
        }
    }

    private fun setUpFiltersRecyclerView(){
        val filters = mutableListOf("Popular", "Top Rated", "Airing Today")
        filtersAdapter = FiltersRecyclerViewAdapter(filters)
        binding.filtersRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.filtersRV.adapter = filtersAdapter
    }

    private fun setUpTvShowsRecyclerView(){
        showsAdapter = TvShowsRecyclerViewAdapter()
        binding.showsRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.showsRV.adapter = showsAdapter
    }

    private fun observe(){
        tvShowsViewModel.getTvShows("popular").observe(viewLifecycleOwner, {
            showsAdapter.submitData(lifecycle, it)
        })
        binding.swipeRefresh.isRefreshing = false
    }


}