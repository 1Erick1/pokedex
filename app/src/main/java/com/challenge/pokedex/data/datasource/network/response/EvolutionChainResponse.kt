package com.challenge.pokedex.data.datasource.network.response

import com.challenge.pokedex.data.datasource.local.util.UrlUtil
import com.challenge.pokedex.data.datasource.network.util.ImageBuilderUtil
import com.challenge.pokedex.domain.entity.Evolution
import com.google.gson.annotations.SerializedName

class PokemonEvolutionsResponse(
    @SerializedName("chain") val evolutionChain: EvolvesToResponse
){
    fun toDomainEntity(): List<Evolution>{
        val list = mutableListOf<SpeciesResponse>()
        extractEvolution(list,evolutionChain)
        return list.map {
            val id = UrlUtil.getIdFromUrl(it.url)
            Evolution(
                id = id,
                name = it.name,
                imageUrl = ImageBuilderUtil.buildFullImageUrl(id)
            )
        }
    }

    fun extractEvolution(list: MutableList<SpeciesResponse>, chain: EvolvesToResponse){
        list.add(chain.species)
        chain.evolvesTo?.forEach {
            extractEvolution(list,it)
        }
    }
}

class SpeciesResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

class EvolvesToResponse(
    @SerializedName("species") val species: SpeciesResponse,
    @SerializedName("evolves_to") val evolvesTo: List<EvolvesToResponse>?
)

