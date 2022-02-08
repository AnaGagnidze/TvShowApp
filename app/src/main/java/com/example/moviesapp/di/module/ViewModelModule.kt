package com.example.moviesapp.di.module

import com.example.moviesapp.ui.TvShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TvShowsViewModel(get()) }
}