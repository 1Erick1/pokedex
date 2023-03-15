package com.challenge.pokedex.di

import com.challenge.pokedex.data.datasource.network.PokedexNetworkDataSourceImpl
import com.challenge.pokedex.data.datasource.network.PokedexNetworkDatasource
import com.challenge.pokedex.data.datasource.network.service.PokedexService
import com.challenge.pokedex.data.repository.PokedexRepositoryImpl
import com.challenge.pokedex.domain.repository.PokedexRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single<PokedexService> { get<Retrofit>().create(PokedexService::class.java)}
    single<PokedexNetworkDatasource> {PokedexNetworkDataSourceImpl(get())}
    single<PokedexRepository> { PokedexRepositoryImpl(get())}
}