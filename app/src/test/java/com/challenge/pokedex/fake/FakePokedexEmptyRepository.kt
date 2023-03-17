package com.challenge.pokedex.fake

import com.challenge.pokedex.domain.entity.Evolution
import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.PokemonResult
import com.challenge.pokedex.domain.repository.PokedexRepository

class FakePokedexEmptyRepository: PokedexRepository {

    override suspend fun fetchAllPokemon(): List<PokemonResult> {
        return listOf()
    }

    override suspend fun searchPokemonByName(query: String): List<PokemonResult> {
        return listOf()
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetail {
        TODO("Not yet implemented")
    }

    override suspend fun downloadThumbnails(list: List<PokemonResult>) {
        TODO("Not yet implemented")
    }

    override suspend fun getEvolutions(id: String): List<Evolution> {
        return listOf()
    }
}