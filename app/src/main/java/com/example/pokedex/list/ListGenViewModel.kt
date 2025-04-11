package com.example.pokedex.list

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.MainRepository
import com.example.pokedex.api.ApiResponseStatus
import com.example.pokedex.api.Pokemon
import kotlinx.coroutines.launch
import java.net.UnknownHostException

private val TAG = ListGenViewModel::class.java.simpleName

class ListGenViewModel(application: FragmentActivity, private val offset: Int, private val limit: Int) : AndroidViewModel(application.application) {


    private var _pokemonList = MutableLiveData<MutableList<Pokemon>>()
    val pokemonList: LiveData<MutableList<Pokemon>>
        get() = _pokemonList

    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus> get() = _status

    private val repository = MainRepository()

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList(){
        viewModelScope.launch {
            try{
                _status.value = ApiResponseStatus.LOADING
                _pokemonList.value = repository.fetchPokemon(offset, limit)
                _status.value = ApiResponseStatus.DONE
            }catch (error:UnknownHostException){
                _status.value = ApiResponseStatus.ERROR
                Log.d(TAG, "No internet Connection")
            }


        }
    }

}