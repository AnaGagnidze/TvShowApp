package com.example.moviesapp.repository

import com.example.moviesapp.models.genresModels.GenresResponseModel
import com.example.moviesapp.network.genresService.GenresServiceHelper
import retrofit2.Response

class GenresRepository(private val genresServiceHelper: GenresServiceHelper) {
    suspend fun getGenres(): Response<GenresResponseModel> = genresServiceHelper.getGenres()
}