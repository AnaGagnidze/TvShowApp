package com.example.moviesapp.network

import com.example.moviesapp.models.genresModels.GenresResponseModel
import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.network.TvShowsService
import com.example.moviesapp.network.TvShowsServiceHelper
import retrofit2.Response

class TvShowsServiceHelperImpl(private val tvShowsService: TvShowsService): TvShowsServiceHelper {
    override suspend fun getPopularShows(): Response<ShowsResponseModel>  = tvShowsService.getPopularShows()

    override suspend fun getTopRatedShows(): Response<ShowsResponseModel> = tvShowsService.getTopRatedShows()

    override suspend fun getAiringTodayShows(): Response<ShowsResponseModel> = tvShowsService.getAiringTodayShows()

    override suspend fun getGenres(): Response<GenresResponseModel> = tvShowsService.getGenres()

}