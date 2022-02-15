package com.example.moviesapp.ui.tvShows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.moviesapp.adapters.tvShowsAdapter.TvShowsDataSource
import com.example.moviesapp.models.showsModels.ShowsResultModel
import com.example.moviesapp.repository.TvShowsRepository

class TvShowsViewModel(private val tvShowsRepository: TvShowsRepository) : ViewModel() {

    fun getTvShows(keyWord: String): LiveData<PagingData<ShowsResultModel>> {
        return Pager(PagingConfig(pageSize = 1)) {
            TvShowsDataSource(tvShowsRepository, keyWord)
        }.liveData.cachedIn(viewModelScope)
    }
}

