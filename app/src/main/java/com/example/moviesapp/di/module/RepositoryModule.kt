package com.example.moviesapp.di.module

import com.example.moviesapp.network.TvShowsServiceHelper
import com.example.moviesapp.network.TvShowsServiceHelperImpl
import com.example.moviesapp.repository.TvShowsRepository
import org.koin.dsl.module

val repoModule = module {
    single { TvShowsRepository(get()) }
    single<TvShowsServiceHelper> {
        return@single TvShowsServiceHelperImpl(get())
    }
}