package com.example.moviesapp.network

import com.example.moviesapp.models.PopularShowsModel
import com.example.moviesapp.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowsService {
    @GET("popular")
    suspend fun getShows(
        @Query("api_key") api_key: String? = API_KEY,
        @Query("page") page: Int? = 1
    ): Response<PopularShowsModel>
}