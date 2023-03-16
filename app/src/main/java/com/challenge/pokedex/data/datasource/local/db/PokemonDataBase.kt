package com.challenge.pokedex.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.pokedex.data.datasource.local.dao.PokemonDao
import com.challenge.pokedex.data.datasource.local.dto.PokemonDto

@Database(entities = [PokemonDto::class], version = 1)
abstract class PokemonDataBase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}