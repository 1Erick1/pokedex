package com.challenge.pokedex.domain.interactor

import com.challenge.pokedex.domain.entity.Evolution
import com.challenge.pokedex.domain.repository.PokedexRepository

class GetPokemonEvolutionsInteractor(
    private val pokedexRepository: PokedexRepository
) {
    suspend fun execute(id: String): List<Evolution>{
        return pokedexRepository.getEvolutions(id)
    }
}