package com.challenge.pokedex.data.datasource.local

import com.challenge.pokedex.data.datasource.local.dao.PokemonDao
import com.challenge.pokedex.data.datasource.local.dto.PokemonDto
import com.challenge.pokedex.data.datasource.local.util.ImageDownloader
import com.challenge.pokedex.domain.entity.PokemonResult

class PokemonLocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val imageDownloader: ImageDownloader
): PokemonLocalDataSource {

    override suspend fun fetchAllPokemon(): List<PokemonResult> {
        return pokemonDao.fetchAllPokemons()
            .map { it.toDomainEntity() }
    }

    override suspend fun searchPokemonByName(query: String): List<PokemonResult> {
        return pokemonDao.searchPokemonByName(query)
            .map { it.toDomainEntity() }
    }

    override suspend fun savePokemon(pokemonResult: PokemonResult) {
        pokemonDao.insert(PokemonDto.fromDomainEntity(pokemonResult))
    }

    override suspend fun downloadThumbnails(pokemons: List<PokemonResult>) {
        pokemons.forEach {
            if (it.thumbnailLocalPath.isNullOrEmpty()) {
                val thumbnailPath = imageDownloader.downloadImage(it.id,it.thumbnailUrl)
                pokemonDao.insert(
                    PokemonDto.fromDomainEntity(it)
                        .apply { thumbnailLocalPath = thumbnailPath }
                )
            }
        }
    }


}