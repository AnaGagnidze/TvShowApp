package com.example.moviesapp.models.showsModels

import com.example.moviesapp.models.showsModels.ShowsResultModel
import com.google.gson.annotations.SerializedName

data class ShowsResponseModel(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<ShowsResultModel>?,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
