package com.challenge.pokedex.domain.repository

import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.PokemonResult

interface PokedexRepository {
    suspend fun fetchAllPokemon(): List<PokemonResult>

    suspend fun searchPokemonByName(query: String): List<PokemonResult>

    suspend fun getPokemonDetail(id: String): PokemonDetail

    suspend fun downloadThumbnails(list: List<PokemonResult>)
}