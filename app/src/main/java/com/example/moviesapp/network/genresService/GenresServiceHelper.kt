package com.example.moviesapp.network.genresService

import com.example.moviesapp.models.genresModels.GenresResponseModel
import retrofit2.Response

interface GenresServiceHelper {
    suspend fun getGenres(): Response<GenresResponseModel>
}