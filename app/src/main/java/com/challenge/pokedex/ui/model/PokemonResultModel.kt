package com.challenge.pokedex.ui.model

import com.challenge.pokedex.domain.entity.PokemonResult
import com.challenge.pokedex.ui.extensions.capitalizeFirst

class PokemonResultModel(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val thumbnailLocalPath: String?
){
    companion object{
        fun fromDomainEntity(pokemonResult: PokemonResult): PokemonResultModel{
            return PokemonResultModel(
                id = pokemonResult.id,
                name = pokemonResult.name.capitalizeFirst(),
                thumbnailUrl = pokemonResult.thumbnailUrl,
                thumbnailLocalPath = pokemonResult.thumbnailLocalPath
            )
        }
    }
}