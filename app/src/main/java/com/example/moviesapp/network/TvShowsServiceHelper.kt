package com.example.moviesapp.network

import com.example.moviesapp.models.genresModels.GenresResponseModel
import com.example.moviesapp.models.showDetailModels.ShowDetailResponseModel
import com.example.moviesapp.models.showsModels.ShowsResponseModel
import retrofit2.Response

interface TvShowsServiceHelper {
    suspend fun getShows(showType: String, page: Int): Response<ShowsResponseModel>
    suspend fun getShowDetails(tvId: Int?) : Response<ShowDetailResponseModel>
    suspend fun getSimilarShows(tvId: Int?) : Response<ShowsResponseModel>
}