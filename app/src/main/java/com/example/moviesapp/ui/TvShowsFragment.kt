package com.example.moviesapp.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapters.FiltersRecyclerViewAdapter
import com.example.moviesapp.base.BaseFragment
import com.example.moviesapp.databinding.TvShowsFragmentBinding

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>(TvShowsFragmentBinding::inflate) {

    private lateinit var adapter: FiltersRecyclerViewAdapter

    override fun setUpFragment() {
        setUpFiltersRecyclerView()
    }

    private fun setUpFiltersRecyclerView(){
        val filters = mutableListOf("Popular", "Top Rated", "Latest")
        adapter = FiltersRecyclerViewAdapter(filters)
        binding.filtersRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.filtersRV.adapter = adapter
    }


}