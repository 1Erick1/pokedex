package com.challenge.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.pokedex.domain.interactor.GetPokemonEvolutionsInteractor
import com.challenge.pokedex.ui.base.BaseViewModel
import com.challenge.pokedex.ui.model.EvolutionModel

class EvolutionsViewModel(
    private val getPokemonEvolutionsInteractor: GetPokemonEvolutionsInteractor
): BaseViewModel() {
    private val _evolutions = MutableLiveData<List<EvolutionModel>>()
    val evolutions: LiveData<List<EvolutionModel>> = _evolutions

    private val _empty = MutableLiveData<Boolean>()
    val empty : LiveData<Boolean> = _empty

    fun getEvolutions(id: String){
        execute {
            val list = getPokemonEvolutionsInteractor.execute(id)
            if (list.size<=1){
                _empty.postValue(true)
            }else{
                _evolutions.postValue(list.map { EvolutionModel.fromEntity(it) })
            }
        }
    }
}