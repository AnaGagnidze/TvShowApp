package com.example.moviesapp.models.showsModels

import com.example.moviesapp.utils.Constants
import com.google.gson.annotations.SerializedName

data class ShowsResultModel(
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double
){
    fun generatePosterUrl(): String{
        return Constants.IMAGE_URL_W500 + posterPath
    }
}
