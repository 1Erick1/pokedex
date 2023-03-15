package com.challenge.pokedex.domain.interactor

import com.challenge.pokedex.domain.entity.PokemonResult
import com.challenge.pokedex.domain.repository.PokedexRepository

class FetchAllPokemonInteractor(
    private val pokedexRepository: PokedexRepository
) {
    suspend fun execute(): List<PokemonResult>{
        return pokedexRepository.fetchAllPokemon()
    }
}