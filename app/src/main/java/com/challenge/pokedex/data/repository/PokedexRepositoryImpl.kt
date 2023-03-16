package com.challenge.pokedex.data.repository

import com.challenge.pokedex.data.datasource.local.PokemonLocalDataSource
import com.challenge.pokedex.data.datasource.network.PokedexNetworkDatasource
import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.PokemonResult
import com.challenge.pokedex.domain.repository.PokedexRepository

class PokedexRepositoryImpl(
    private val pokedexNetworkDatasource: PokedexNetworkDatasource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
): PokedexRepository {

    override suspend fun fetchAllPokemon(): List<PokemonResult> {
        val local = pokemonLocalDataSource.fetchAllPokemon()
        return if (local.isNotEmpty()) {
            local
        } else {
            val results = pokedexNetworkDatasource.fetchAllPokemon()
            results.forEach {
                pokemonLocalDataSource.savePokemon(it)
            }
            results
        }
    }

    override suspend fun searchPokemonByName(query: String): List<PokemonResult> {
        return pokemonLocalDataSource.searchPokemonByName(query.lowercase())
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetail {
        TODO("Not yet implemented")
    }

    override suspend fun downloadThumbnails(list: List<PokemonResult>) {
        pokemonLocalDataSource.downloadThumbnails(list)
    }
}