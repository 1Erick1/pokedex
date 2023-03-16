package com.challenge.pokedex.di

import android.content.Context
import androidx.room.Room
import com.challenge.pokedex.data.datasource.local.PokemonLocalDataSource
import com.challenge.pokedex.data.datasource.local.PokemonLocalDataSourceImpl
import com.challenge.pokedex.data.datasource.local.db.PokemonDataBase
import com.challenge.pokedex.data.datasource.local.util.ImageDownloader
import com.challenge.pokedex.data.datasource.network.PokedexNetworkDataSourceImpl
import com.challenge.pokedex.data.datasource.network.PokedexNetworkDatasource
import com.challenge.pokedex.data.datasource.network.service.PokedexService
import com.challenge.pokedex.data.repository.PokedexRepositoryImpl
import com.challenge.pokedex.domain.repository.PokedexRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    //Network
    single<PokedexService> { get<Retrofit>().create(PokedexService::class.java)}
    single<PokedexNetworkDatasource> {PokedexNetworkDataSourceImpl(get())}

    //Local
    single { providePokemonDatabase(androidContext()) }
    single { get<PokemonDataBase>().pokemonDao()}
    single { ImageDownloader(androidContext(),OkHttpClient.Builder().build()) }
    single<PokemonLocalDataSource> { PokemonLocalDataSourceImpl(get(),get()) }

    //Repository
    single<PokedexRepository> { PokedexRepositoryImpl(get(),get())}
}

fun providePokemonDatabase(context: Context): PokemonDataBase{
    return Room.databaseBuilder(context, PokemonDataBase::class.java,"Pokemon.db")
        .build()
}