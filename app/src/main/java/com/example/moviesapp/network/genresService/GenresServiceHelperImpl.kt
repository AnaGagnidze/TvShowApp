package com.example.moviesapp.network.genresService

import com.example.moviesapp.models.genresModels.GenresResponseModel
import retrofit2.Response

class GenresServiceHelperImpl(private val genresService: GenresService):GenresServiceHelper {
    override suspend fun getGenres(): Response<GenresResponseModel> = genresService.getGenres()
}