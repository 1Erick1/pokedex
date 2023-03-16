package com.challenge.pokedex.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    protected val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val _error = MutableLiveData<Throwable>()
    val error : LiveData<Throwable> = _error


    fun execute(func: suspend ()->Unit){
        viewModelScope.launch {
            try {
                _progress.postValue(true)
                func()
                _progress.postValue(false)
            } catch (e: Exception){
                e.printStackTrace()
                _progress.postValue(false)
                _error.postValue(e)
            }
        }
    }

}