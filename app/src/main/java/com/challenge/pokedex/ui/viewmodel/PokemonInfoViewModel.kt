package com.challenge.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.pokedex.domain.interactor.GetPokemonDetailInteractor
import com.challenge.pokedex.ui.base.BaseViewModel
import com.challenge.pokedex.ui.model.PokemonDetailModel

class PokemonInfoViewModel(
    private val getPokemonDetailInteractor: GetPokemonDetailInteractor
): BaseViewModel() {
    private val _pokemon = MutableLiveData<PokemonDetailModel>()
    val pokemon: LiveData<PokemonDetailModel> = _pokemon

    fun getPokemonInfo(id: String){
        execute {
            val detail = getPokemonDetailInteractor.execute(id)
            _pokemon.postValue(PokemonDetailModel.fromDomainEntity(detail))
        }
    }
}