package com.example.pokedex.list

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.MainRepository
import com.example.pokedex.api.Pokemon
import kotlinx.coroutines.launch

class ListGenViewModel(application: FragmentActivity, private val offset: Int, private val limit: Int) : AndroidViewModel(application.application) {
    /*
    private var _offset = MutableLiveData<Int>()
    val offset: LiveData<Int>
        get() = _offset

    private var _limit = MutableLiveData<Int>()
    val limit: LiveData<Int>
        get() = _limit

     */

    private var _pokemonList = MutableLiveData<MutableList<Pokemon>>()
    val pokemonList: LiveData<MutableList<Pokemon>>
        get() = _pokemonList

    private val repository = MainRepository()

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList(){
        viewModelScope.launch {

            /*
            try {
            }catch (e: Exception){
                Log.e("Error",e.toString())
            }

             */
            _pokemonList.value = repository.fetchPokemon(offset, limit)

        }
    }

}