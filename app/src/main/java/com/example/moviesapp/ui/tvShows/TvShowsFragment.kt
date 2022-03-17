package com.example.moviesapp.ui.tvShows

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
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

    private var filtersAdapter: FiltersRecyclerViewAdapter? = null
    private var showsAdapter: TvShowsRecyclerViewAdapter? = null

    override fun setUpFragment() {
        setUpFiltersRecyclerView()
        setUpTvShowsRecyclerView()
        observe(FiltersEnum.POPULAR.filterName)
        setListeners()
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            when (filtersAdapter?.currentFilterPosition) {
                0 -> observe(FiltersEnum.POPULAR.filterName)
                1 -> observe(FiltersEnum.TOP_RATED.filterName)
                2 -> observe(FiltersEnum.AIRING_TODAY.filterName)
                else -> observe(FiltersEnum.POPULAR.filterName)
            }
        }

        binding.swipeRefresh.setColorSchemeResources(R.color.primaryDarkColor)
        binding.swipeRefresh.setProgressBackgroundColorSchemeResource(R.color.dark_grey)
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
        filtersAdapter?.onDrawableClick = { position ->
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
        showsAdapter?.addLoadStateListener {
            binding.swipeRefresh.isRefreshing = it.source.refresh is LoadState.Loading
            val errorState =
                it.source.append as? LoadState.Error ?: it.source.prepend as? LoadState.Error
            errorState?.let { state ->
                binding.showsRV.isVisible = false
                binding.errorItemsGr.isVisible = true
                binding.errorText.text = state.error.toString()
            }
        }
        binding.showsRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.showsRV.adapter = showsAdapter
        showsAdapter?.onDrawableClick = {
            findNavController().navigate(
                TvShowsFragmentDirections.actionTvShowsFragmentToTvShowDetailsFragment(
                    it.toString()
                )
            )
        }
    }

    private fun observe(keyword: String) {
        tvShowsViewModel.getTvShows(keyword).observe(viewLifecycleOwner, {
            showsAdapter?.submitData(lifecycle, it)
        })
        binding.swipeRefresh.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        filtersAdapter = null
        showsAdapter = null
    }


}