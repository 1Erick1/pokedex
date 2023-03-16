package com.challenge.pokedex.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.pokedex.data.datasource.local.dto.PokemonDto

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    suspend fun fetchAllPokemons(): List<PokemonDto>

    @Query("SELECT * FROM pokemon WHERE name LIKE :query || '%'")
    suspend fun searchPokemonByName(query: String): List<PokemonDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonDto)
}