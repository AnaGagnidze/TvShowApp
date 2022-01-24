package com.example.moviesapp.di.module

import com.example.moviesapp.network.genresService.GenresServiceHelper
import com.example.moviesapp.network.genresService.GenresServiceHelperImpl
import com.example.moviesapp.network.showsService.TvShowsServiceHelper
import com.example.moviesapp.network.showsService.TvShowsServiceHelperImpl
import com.example.moviesapp.repository.GenresRepository
import com.example.moviesapp.repository.TvShowsRepository
import org.koin.dsl.module

val repoModule = module {
    single { TvShowsRepository(get()) }
    single { GenresRepository(get()) }
    single<TvShowsServiceHelper> {
        return@single TvShowsServiceHelperImpl(get())
    }

    single<GenresServiceHelper> {
        return@single GenresServiceHelperImpl(get())
    }
}