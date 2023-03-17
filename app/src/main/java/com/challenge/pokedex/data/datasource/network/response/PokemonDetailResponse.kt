package com.challenge.pokedex.data.datasource.network.response

import com.challenge.pokedex.data.datasource.network.util.ImageBuilderUtil
import com.challenge.pokedex.domain.entity.Ability
import com.challenge.pokedex.domain.entity.Move
import com.challenge.pokedex.domain.entity.PokemonDetail
import com.challenge.pokedex.domain.entity.Type
import com.google.gson.annotations.SerializedName

class PokemonDetailResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("types") val types: List<TypeResponse>,
    @SerializedName("abilities") val abilities: List<AbilityResponse>,
    @SerializedName("moves") val moves: List<MoveResponse>
){
    fun toDomainEntity(): PokemonDetail{
        return PokemonDetail(
            id = id,
            name = name,
            height = height,
            weight = weight,
            image = ImageBuilderUtil.buildFullImageUrl(id),
            types = types.map { Type(it.type.name,it.type.url) },
            abilities = abilities.map { Ability(it.ability.name,it.ability.url) },
            moves = moves.map { Move(it.move.name,it.move.url) }
        )
    }
}

class TypeResponse(
    @SerializedName("type") val type: GenericInfoResponse
)

class AbilityResponse(
    @SerializedName("ability") val ability: GenericInfoResponse
)

class MoveResponse(
    @SerializedName("move") val move: GenericInfoResponse
)

class GenericInfoResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)