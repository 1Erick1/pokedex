package com.challenge.pokedex.di

import android.content.Context
import com.challenge.pokedex.BuildConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit(androidContext()) }
}

private fun provideRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(ChuckerInterceptor(context))
                .build())
        .build()
}