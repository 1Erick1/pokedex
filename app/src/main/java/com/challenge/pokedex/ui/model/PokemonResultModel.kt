package com.challenge.pokedex.ui.model

import com.challenge.pokedex.domain.entity.PokemonResult

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
                name = pokemonResult.name.replaceFirstChar { it.uppercase() },
                thumbnailUrl = pokemonResult.thumbnailUrl,
                thumbnailLocalPath = pokemonResult.thumbnailLocalPath
            )
        }
    }
}