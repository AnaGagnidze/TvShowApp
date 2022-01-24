package com.example.moviesapp.network.genresService

import com.example.moviesapp.models.genresModels.GenresResponseModel
import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresService {
    @GET("/genre/tv/list")
    suspend fun getGenres(
        @Query("api_key") api_key: String? = Constants.API_KEY
    ): Response<GenresResponseModel>
}