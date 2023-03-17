package com.challenge.pokedex.ui.model

import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.Type
import com.challenge.pokedex.ui.extensions.capitalizeFirst

class PokemonDetailModel(
    val id: String,
    val name: String,
    val image: String,
    val weight: String,
    val height: String,
    val types: List<Type>,
    val abilities: String,
    val moves: String
) {
    companion object{
        fun fromDomainEntity(pokemon: PokemonDetail): PokemonDetailModel{
            return pokemon.let {
                PokemonDetailModel(
                    id = it.id,
                    name = it.name.capitalizeFirst(),
                    image = it.image,
                    weight = "${it.weight} Kg",
                    height = "${it.height} m",
                    types = it.types,
                    abilities = it.abilities.joinToString {
                        it.name.capitalizeFirst()
                    }.replace("-"," "),
                    moves = it.moves.joinToString{
                        it.name.capitalizeFirst()
                    }.replace("-","")
                )
            }
        }
    }
}