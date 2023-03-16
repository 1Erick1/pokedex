package com.challenge.pokedex.domain.interactor

import com.challenge.pokedex.domain.entity.PokemonResult
import com.challenge.pokedex.domain.repository.PokedexRepository

class DownloadThumbnailsInteractor(
    private val pokedexRepository: PokedexRepository
) {
    suspend fun execute(pokemons: List<PokemonResult>){
        pokedexRepository.downloadThumbnails(pokemons)
    }
}