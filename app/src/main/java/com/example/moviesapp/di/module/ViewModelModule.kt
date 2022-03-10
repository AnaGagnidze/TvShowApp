package com.example.moviesapp.di.module

import com.example.moviesapp.ui.tvShowDetails.TvShowDetailsViewModel
import com.example.moviesapp.ui.tvShows.TvShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TvShowsViewModel(get()) }
    viewModel { (tvId: String?) ->
        TvShowDetailsViewModel(tvId, get()) }
}