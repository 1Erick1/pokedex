package com.challenge.pokedex.data.datasource.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.pokedex.domain.entity.PokemonResult

@Entity(tableName = "pokemon")
data class PokemonDto(
    @PrimaryKey val id: String,
    val name: String,
    val thumbnailUrl: String,
    var thumbnailLocalPath: String? = null
){
    fun toDomainEntity(): PokemonResult{
        return PokemonResult(
            id = id,
            name = name,
            thumbnailLocalPath = thumbnailLocalPath,
            thumbnailUrl = thumbnailUrl
        )
    }

    companion object{
        fun fromDomainEntity(pokemon: PokemonResult): PokemonDto{
            return PokemonDto(
                id = pokemon.id,
                name = pokemon.name,
                thumbnailUrl = pokemon.thumbnailUrl
            )
        }
    }
}