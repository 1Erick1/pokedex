package com.challenge.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.pokedex.domain.interactor.DownloadThumbnailsInteractor
import com.challenge.pokedex.domain.interactor.FetchAllPokemonInteractor
import com.challenge.pokedex.domain.interactor.SearchPokemonByNameInteractor
import com.challenge.pokedex.ui.base.BaseViewModel
import com.challenge.pokedex.ui.model.PokemonResultModel

class MainViewModel(
    private val fetchAllPokemonInteractor: FetchAllPokemonInteractor,
    private val searchPokemonByNameInteractor: SearchPokemonByNameInteractor,
    private val downloadThumbnailsInteractor: DownloadThumbnailsInteractor
): BaseViewModel() {
    private val _pokemons = MutableLiveData<List<PokemonResultModel>>()
    val pokemons: LiveData<List<PokemonResultModel>> = _pokemons

    private val _empty = MutableLiveData<Boolean>()
    val empty : LiveData<Boolean> = _empty

    fun fetchAllPokemon(){
        execute {
            val results = fetchAllPokemonInteractor.execute()
            _pokemons.postValue(results.map { PokemonResultModel.fromDomainEntity(it) })
            _progress.value = false
            if (results.any { it.thumbnailLocalPath.isNullOrEmpty() }){
                downloadThumbnailsInteractor.execute(results)
            }
        }
    }

    fun searchByName(query: String){
        execute {
            val results = searchPokemonByNameInteractor.execute(query)
            if (results.isEmpty()){
                _empty.postValue(true)
            } else {
                _pokemons.postValue(results.map { PokemonResultModel.fromDomainEntity(it) })
            }
        }
    }
}