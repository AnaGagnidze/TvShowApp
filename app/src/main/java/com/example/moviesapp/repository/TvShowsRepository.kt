package com.example.moviesapp.repository

import com.example.moviesapp.models.genresModels.GenresResponseModel
import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.network.TvShowsServiceHelper
import retrofit2.Response

class TvShowsRepository(private val tvShowsServiceHelper: TvShowsServiceHelper) {
    suspend fun getShows(showType: String, page: Int): Response<ShowsResponseModel> =
        tvShowsServiceHelper.getShows(showType, page)

    suspend fun getGenres(): Response<GenresResponseModel> = tvShowsServiceHelper.getGenres()
}