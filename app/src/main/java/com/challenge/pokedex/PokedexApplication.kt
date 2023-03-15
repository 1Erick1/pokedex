package com.challenge.pokedex

import android.app.Application
import com.challenge.pokedex.di.dataModule
import com.challenge.pokedex.di.interactorModule
import com.challenge.pokedex.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(listOf(networkModule, dataModule, interactorModule))
        }
    }
}