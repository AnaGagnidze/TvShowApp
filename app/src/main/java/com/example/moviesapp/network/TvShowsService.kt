package com.example.moviesapp.network

import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowsService {
    @GET("tv/popular")
    suspend fun getPopularShows(
        @Query("api_key") api_key: String? = API_KEY,
        @Query("page") page: Int? = 1
    ): Response<ShowsResponseModel>

    @GET("tv/top_rated")
    suspend fun getTopRatedShows(
        @Query("api_key") api_key: String? = API_KEY,
        @Query("page") page: Int? = 1
    ): Response<ShowsResponseModel>

    @GET("tv/airing_today")
    suspend fun getAiringTodayShows(
        @Query("api_key") api_key: String? = API_KEY,
        @Query("page") page: Int? = 1
    ): Response<ShowsResponseModel>
}