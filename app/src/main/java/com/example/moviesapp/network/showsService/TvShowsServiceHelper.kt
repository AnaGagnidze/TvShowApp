package com.example.moviesapp.network.showsService

import com.example.moviesapp.models.showsModels.ShowsResponseModel
import retrofit2.Response

interface TvShowsServiceHelper {
    suspend fun getPopularShows(): Response<ShowsResponseModel>
    suspend fun getTopRatedShows(): Response<ShowsResponseModel>
    suspend fun getAiringTodayShows(): Response<ShowsResponseModel>
}