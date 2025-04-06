package com.example.pokedex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.Pokemon
import com.example.pokedex.api.PokemonList
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {


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
            _pokemonList.value = repository.fetchPokemon()

        }
    }

}