package com.challenge.pokedex.data.datasource.network.response

import com.challenge.pokedex.data.datasource.network.util.ImageBuilderUtil
import com.challenge.pokedex.domain.entity.PokemonResult
import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("results") val results: List<PokemonResultResponse>
)

data class PokemonResultResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
){
    fun toDomainEntity(): PokemonResult{
        val id = getIdFromUrl()
        return PokemonResult(
            id = id,
            name = name,
            thumbnailUrl = ImageBuilderUtil.buildThumbnailUrl(id),
            url = url
        )
    }

    private fun getIdFromUrl(): String{
        return url.split("/".toRegex()).dropLast(1).last()
    }
}