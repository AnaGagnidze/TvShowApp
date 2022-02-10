package com.example.moviesapp.di.module

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.adapters.tvShowsAdapter.TvShowsDataSource
import com.example.moviesapp.network.TvShowsService
import com.example.moviesapp.network.TvShowsServiceHelper
import com.example.moviesapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideTvShowsService(retrofit: Retrofit): TvShowsService = retrofit.create(
    TvShowsService::class.java)

private fun provideTvShowsHelper(tvShowsServiceHelper: TvShowsServiceHelper): TvShowsServiceHelper = tvShowsServiceHelper



val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), Constants.SHOWS_BASE_URL) }
    single { provideTvShowsService(get()) }
    factory { TvShowsDataSource(get(), get()) }
}