package com.challenge.pokedex.domain.entity

data class PokemonResult(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val thumbnailLocalPath: String? = null,
    val url: String? = null
)