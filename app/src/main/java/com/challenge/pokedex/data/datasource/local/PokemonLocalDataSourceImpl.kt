package com.challenge.pokedex.data.datasource.local

import android.util.Log
import com.challenge.pokedex.data.datasource.local.dao.PokemonDao
import com.challenge.pokedex.data.datasource.local.dto.PokemonDto
import com.challenge.pokedex.data.datasource.local.util.ImageDownloader
import com.challenge.pokedex.domain.entity.PokemonResult
import java.util.*

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
                val a = Date().time
                val thumbnailPath = imageDownloader.downloadImage(it.id,it.thumbnailUrl)
                val diff = Date().time - a
                Log.d("XXX","Finished in $diff ms $thumbnailPath")
                pokemonDao.insert(
                    PokemonDto.fromDomainEntity(it)
                        .apply { thumbnailLocalPath = thumbnailPath }
                )
            }
        }
    }


}