package com.challenge.pokedex.di

import com.challenge.pokedex.domain.interactor.*
import org.koin.dsl.module

val interactorModule = module {
    single { FetchAllPokemonInteractor(get()) }
    single { GetPokemonDetailInteractor(get()) }
    single { SearchPokemonByNameInteractor(get()) }
    single { DownloadThumbnailsInteractor(get())}
    single { GetPokemonEvolutionsInteractor(get()) }
}