package com.example.moviesapp.network

import com.example.moviesapp.models.genresModels.GenresResponseModel
import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.network.TvShowsService
import com.example.moviesapp.network.TvShowsServiceHelper
import retrofit2.Response

class TvShowsServiceHelperImpl(private val tvShowsService: TvShowsService) : TvShowsServiceHelper {
    override suspend fun getShows(showType: String, page: Int): Response<ShowsResponseModel> =
        tvShowsService.getShows(showType = showType, page = page)

    override suspend fun getGenres(): Response<GenresResponseModel> = tvShowsService.getGenres()

}