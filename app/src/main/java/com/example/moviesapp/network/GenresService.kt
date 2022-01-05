package com.example.moviesapp.network

import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresService {
    @GET("/genre/tv/list")
    suspend fun getPopularShows(
        @Query("api_key") api_key: String? = Constants.API_KEY,
        @Query("page") page: Int? = 1
    ): Response<ShowsResponseModel>
}