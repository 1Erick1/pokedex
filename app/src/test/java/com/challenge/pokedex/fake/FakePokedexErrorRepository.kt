package com.challenge.pokedex.fake

import com.challenge.pokedex.domain.entity.Evolution
import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.PokemonResult
import com.challenge.pokedex.domain.repository.PokedexRepository

class FakePokedexErrorRepository: PokedexRepository {
    override suspend fun fetchAllPokemon(): List<PokemonResult> {
        throw Exception("An error occurred")
    }

    override suspend fun searchPokemonByName(query: String): List<PokemonResult> {
        throw Exception("An error occurred")
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetail {
        throw Exception("An error occurred")
    }

    override suspend fun downloadThumbnails(list: List<PokemonResult>) {
        throw Exception("An error occurred")
    }

    override suspend fun getEvolutions(id: String): List<Evolution> {
        throw Exception("An error occurred")
    }
}