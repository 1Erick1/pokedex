package com.challenge.pokedex.domain.interactor

import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.repository.PokedexRepository

class GetPokemonDetailInteractor(
    private val pokedexRepository: PokedexRepository
) {
    suspend fun execute(id: String): PokemonDetail{
        return pokedexRepository.getPokemonDetail(id)
    }
}