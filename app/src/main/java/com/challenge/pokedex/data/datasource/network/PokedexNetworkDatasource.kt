package com.challenge.pokedex.data.datasource.network

import com.challenge.pokedex.domain.entity.Evolution
import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.PokemonResult

interface PokedexNetworkDatasource {

    suspend fun fetchAllPokemon(): List<PokemonResult>

    suspend fun getPokemonDetail(id: String): PokemonDetail

    suspend fun getPokemonEvolutions(id: String): List<Evolution>
}
