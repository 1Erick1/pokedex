package com.challenge.pokedex.ui.model

import com.challenge.pokedex.domain.entity.Evolution
import com.challenge.pokedex.ui.extensions.capitalizeFirst

class EvolutionModel(
    val name: String,
    val imageUrl: String
){
    companion object{
        fun fromEntity(evolution: Evolution): EvolutionModel{
            return EvolutionModel(
                name = evolution.name.capitalizeFirst(),
                imageUrl = evolution.imageUrl
            )
        }
    }
}