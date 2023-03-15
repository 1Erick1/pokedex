package com.challenge.pokedex.domain.interactor

import com.challenge.pokedex.domain.entity.PokemonResult
import com.challenge.pokedex.domain.repository.PokedexRepository

class SearchPokemonByNameInteractor(
    private val pokedexRepository: PokedexRepository
) {
    suspend fun execute(query: String): List<PokemonResult>{
        return pokedexRepository.searchPokemonByName(query)
    }
}