package com.challenge.pokedex.data.datasource.network.service

import com.challenge.pokedex.data.datasource.network.response.PokemonDetailResponse
import com.challenge.pokedex.data.datasource.network.response.PokemonListResponse
import com.challenge.pokedex.data.datasource.network.response.PokemonResultResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 151
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: String): PokemonDetailResponse

}