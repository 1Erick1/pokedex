package com.challenge.pokedex.data.datasource.local

import com.challenge.pokedex.domain.entity.PokemonResult

interface PokemonLocalDataSource {

    suspend fun fetchAllPokemon(): List<PokemonResult>

    suspend fun searchPokemonByName(query: String): List<PokemonResult>

    suspend fun savePokemon(pokemonResult: PokemonResult)

    suspend fun downloadThumbnails(pokemons: List<PokemonResult>)

}