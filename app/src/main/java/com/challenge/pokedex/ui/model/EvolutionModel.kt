package com.challenge.pokedex.ui.model

import com.challenge.pokedex.domain.entity.Evolution

class EvolutionModel(
    val name: String,
    val imageUrl: String
){
    companion object{
        fun fromEntity(evolution: Evolution): EvolutionModel{
            return EvolutionModel(
                name = evolution.name.uppercase(),
                imageUrl = evolution.imageUrl
            )
        }
    }
}