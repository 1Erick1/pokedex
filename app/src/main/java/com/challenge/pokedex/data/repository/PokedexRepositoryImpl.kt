package com.challenge.pokedex.data.repository

import com.challenge.pokedex.data.datasource.network.PokedexNetworkDatasource
import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.PokemonResult
import com.challenge.pokedex.domain.repository.PokedexRepository

class PokedexRepositoryImpl(
    private val pokedexNetworkDatasource: PokedexNetworkDatasource
): PokedexRepository {

    override suspend fun fetchAllPokemon(): List<PokemonResult> {
        return pokedexNetworkDatasource.fetchAllPokemon()
    }

    override suspend fun searchPokemonByName(query: String): List<PokemonResult> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetail {
        TODO("Not yet implemented")
    }
}