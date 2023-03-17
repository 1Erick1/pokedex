package com.challenge.pokedex.domain.entity

data class PokemonDetail(
    val id: String,
    val name: String,
    val image: String,
    val weight: Int,
    val height: Int,
    val types: List<Type>,
    val abilities: List<Ability>,
    val moves: List<Move>
)

data class Type(
    val name: String,
    val url: String
)

data class Ability(
    val name: String,
    val url: String
)

data class Move(
    val name: String,
    val url: String
)

