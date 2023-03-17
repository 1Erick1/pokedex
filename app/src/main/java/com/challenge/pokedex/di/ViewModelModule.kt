package com.challenge.pokedex.di

import com.challenge.pokedex.ui.viewmodel.EvolutionsViewModel
import com.challenge.pokedex.ui.viewmodel.MainViewModel
import com.challenge.pokedex.ui.viewmodel.PokemonInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(),get(),get()) }
    viewModel { PokemonInfoViewModel(get()) }
    viewModel { EvolutionsViewModel(get()) }
}