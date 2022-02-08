package com.example.moviesapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.network.ResultControl
import com.example.moviesapp.repository.TvShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowsViewModel(private val tvShowsRepository: TvShowsRepository) : ViewModel() {

    private val tvShows = MutableLiveData<ResultControl<ShowsResponseModel>>().apply {
        mutableListOf<ShowsResponseModel>()
    }
    val _tvShows: LiveData<ResultControl<ShowsResponseModel>> = tvShows

    fun initTvShows(keyWord: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getTvShows(keyWord)
            }
        }
    }

    private suspend fun getTvShows(keyWord: String = "") {
        tvShows.postValue(ResultControl.loading(true))
        val result = when (keyWord) {
            "Popular" -> tvShowsRepository.getPopularShows()
            "Top" -> tvShowsRepository.getTopRatedShows()
            "Today" -> tvShowsRepository.getAiringTodayShows()
            else -> tvShowsRepository.getPopularShows()
        }

        if (result.isSuccessful) {
            result.body()?.let {
                tvShows.postValue(ResultControl.success(it))
            }

        } else {
            tvShows.postValue(ResultControl.error(result.message()))
        }
    }

}