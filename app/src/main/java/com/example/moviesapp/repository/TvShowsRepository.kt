package com.example.moviesapp.repository

import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.network.showsService.TvShowsServiceHelper
import retrofit2.Response

class TvShowsRepository(private val tvShowsServiceHelper: TvShowsServiceHelper) {
    suspend fun getPopularShows(): Response<ShowsResponseModel> = tvShowsServiceHelper.getPopularShows()
    suspend fun getTopRatedShows(): Response<ShowsResponseModel> = tvShowsServiceHelper.getTopRatedShows()
    suspend fun getAiringTodayShows(): Response<ShowsResponseModel> = tvShowsServiceHelper.getAiringTodayShows()
}