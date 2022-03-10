package com.example.moviesapp.network

import com.example.moviesapp.models.genresModels.GenresResponseModel
import com.example.moviesapp.models.showDetailModels.ShowDetailResponseModel
import com.example.moviesapp.models.showsModels.ShowsResponseModel
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowsService {
    @GET("tv/{showType}")
    suspend fun getShows(
        @Path("showType") showType: String?,
        @Query("api_key") api_key: String? = API_KEY,
        @Query("page") page: Int? = 1
    ): Response<ShowsResponseModel>

    @GET("tv/{tv_id}")
    suspend fun getShowDetails(
        @Path("tv_id") tvId: Int?,
        @Query("api_key") api_key: String? = API_KEY
    ) : Response<ShowDetailResponseModel>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarShows(
        @Path("tv_id") tvId: Int?,
        @Query("api_key") api_key: String? = API_KEY,
        @Query("page") page: Int? = 1
    ) : Response<ShowsResponseModel>
}