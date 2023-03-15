package com.challenge.pokedex.data.datasource.network.util

object ImageBuilderUtil {
    fun buildThumbnailUrl(id: String): String{
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }

    fun buildFullImageUrl(id: String): String{
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}