package com.example.moviesapp.repository

import com.example.moviesapp.models.showDetailModels.ShowDetailResponseModel
import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.network.TvShowsServiceHelper
import retrofit2.Response

class TvShowsRepository(private val tvShowsServiceHelper: TvShowsServiceHelper) {
    suspend fun getShows(showType: String, page: Int): Response<ShowsResponseModel> =
        tvShowsServiceHelper.getShows(showType, page)

    suspend fun getShowDetails(tvId: Int?): Response<ShowDetailResponseModel> =
        tvShowsServiceHelper.getShowDetails(tvId)

    suspend fun getSimilarShows(tvId: Int?): Response<ShowsResponseModel> =
        tvShowsServiceHelper.getSimilarShows(tvId)
}