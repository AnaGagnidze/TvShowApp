package com.example.moviesapp.ui.tvShowDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.moviesapp.adapters.tvShowsAdapter.TvShowsDataSource
import com.example.moviesapp.models.showDetailModels.ShowDetailResponseModel
import com.example.moviesapp.models.showsModels.ShowsResultModel
import com.example.moviesapp.network.ResultControl
import com.example.moviesapp.repository.TvShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowDetailsViewModel(private val tvId: String?,
                             private val tvShowsRepository: TvShowsRepository): ViewModel() {

    private val showDetails = MutableLiveData<ResultControl<ShowDetailResponseModel?>>().apply {
        mutableListOf<ShowDetailResponseModel>()
    }

    val _showDetails: LiveData<ResultControl<ShowDetailResponseModel?>> = showDetails


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getShowDetails(tvId?.toInt())
            }
        }
    }

    private suspend fun getShowDetails(tvId: Int?) {
        val result = tvShowsRepository.getShowDetails(tvId)
        if (result.isSuccessful) {
            val info = result.body()
            info.let {
                showDetails.postValue(ResultControl.success(it))
            }
        }else {
            showDetails.postValue(ResultControl.error(result.errorBody().toString()))
        }
    }

    fun getSimilarShows(): LiveData<PagingData<ShowsResultModel>> {
        return Pager(PagingConfig(pageSize = 1)) {
            TvShowsDataSource(tvShowsRepository = tvShowsRepository, tvId = tvId?.toInt())
        }.liveData.cachedIn(viewModelScope)
    }
}

