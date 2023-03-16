package com.challenge.pokedex.di

import com.challenge.pokedex.domain.interactor.DownloadThumbnailsInteractor
import com.challenge.pokedex.domain.interactor.FetchAllPokemonInteractor
import com.challenge.pokedex.domain.interactor.GetPokemonDetailInteractor
import com.challenge.pokedex.domain.interactor.SearchPokemonByNameInteractor
import org.koin.dsl.module

val interactorModule = module {
    single { FetchAllPokemonInteractor(get()) }
    single { GetPokemonDetailInteractor(get()) }
    single { SearchPokemonByNameInteractor(get()) }
    single { DownloadThumbnailsInteractor(get())}
}