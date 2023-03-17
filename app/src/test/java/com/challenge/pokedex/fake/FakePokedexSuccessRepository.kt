package com.challenge.pokedex.fake

import com.challenge.pokedex.domain.entity.Evolution
import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.PokemonResult
import com.challenge.pokedex.domain.repository.PokedexRepository
import java.util.Collections

class FakePokedexSuccessRepository: PokedexRepository {

    override suspend fun fetchAllPokemon(): List<PokemonResult> {
        val pokemon = PokemonResult("1","Bulbasaur","url")
        return Collections.nCopies(10,pokemon)
    }

    override suspend fun searchPokemonByName(query: String): List<PokemonResult> {
        return Collections.nCopies(2,PokemonResult("1","Bulbasaur","url"))
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetail {
        return PokemonDetail(
            "1","","",10,10, listOf(), listOf(), listOf()
        )
    }

    override suspend fun downloadThumbnails(list: List<PokemonResult>) {
    }

    override suspend fun getEvolutions(id: String): List<Evolution> {
        return Collections.nCopies(3,Evolution("1","Bulbasaur","url"))
    }
}