package com.example.moviesapp.adapters.tvShowsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.models.showsModels.ShowsResultModel

object TvShowsComparator : DiffUtil.ItemCallback<ShowsResultModel>() {
    override fun areItemsTheSame(oldItem: ShowsResultModel, newItem: ShowsResultModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ShowsResultModel, newItem: ShowsResultModel): Boolean {
        return oldItem == newItem
    }

}