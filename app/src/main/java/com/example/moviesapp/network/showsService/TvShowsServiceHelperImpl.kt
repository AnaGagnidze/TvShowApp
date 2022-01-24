package com.example.moviesapp.network.showsService

import com.example.moviesapp.models.showsModels.ShowsResponseModel
import retrofit2.Response

class TvShowsServiceHelperImpl(private val tvShowsService: TvShowsService): TvShowsServiceHelper {
    override suspend fun getPopularShows(): Response<ShowsResponseModel>  = tvShowsService.getPopularShows()

    override suspend fun getTopRatedShows(): Response<ShowsResponseModel> = tvShowsService.getTopRatedShows()

    override suspend fun getAiringTodayShows(): Response<ShowsResponseModel> = tvShowsService.getAiringTodayShows()
}