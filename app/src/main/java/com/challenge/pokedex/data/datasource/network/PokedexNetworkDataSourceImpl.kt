package com.challenge.pokedex.data.datasource.network

import com.challenge.pokedex.data.datasource.network.service.PokedexService
import com.challenge.pokedex.domain.entity.Evolution
import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.PokemonResult

class PokedexNetworkDataSourceImpl(
    private val pokedexService: PokedexService
): PokedexNetworkDatasource {

    override suspend fun fetchAllPokemon(): List<PokemonResult> {
        return pokedexService.fetchPokemonList().results.map {
            it.toDomainEntity()
        }
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetail {
        return pokedexService.getPokemonDetail(id).toDomainEntity()
    }

    override suspend fun getPokemonEvolutions(id: String): List<Evolution> {
        return listOf()
    }

}