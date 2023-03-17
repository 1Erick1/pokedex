package com.challenge.pokedex.data.datasource.network.response

import com.google.gson.annotations.SerializedName

class PokemonSpeciesResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("evolution_chain") val evolutionChain: ChainResponse
)

class ChainResponse(
    @SerializedName("url") val url: String
)