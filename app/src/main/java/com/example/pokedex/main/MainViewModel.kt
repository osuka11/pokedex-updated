package com.example.pokedex.main

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.ApiResponseStatus
import com.example.pokedex.api.Pokemon
import com.example.pokedex.database.getDatabase
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var _pokeList = MutableLiveData<MutableList<Pokemon>>()
    val pokeList: LiveData<MutableList<Pokemon>> get() = _pokeList

    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus> get() = _status

    private val _currentId = MutableLiveData<Long>()
    val currentId: LiveData<Long> get() = _currentId

    private val _currentPokemon = MutableLiveData<Pokemon>()
    val currentPokemon :LiveData<Pokemon> get() = _currentPokemon



    private val _offset = MutableLiveData<Int>()
    val offset: LiveData<Int> get() = _offset


    private val _limit = MutableLiveData<Int>()
    val limit : LiveData<Int> get () = _limit




    val database = getDatabase(application)


    val repository = MainRepository(database)


    init {
        setCurrentId(0)
        setOffsetLimit(1,150)
        viewModelScope.launch {
            fetchPokemonListByDataBase()
        }


    }

    private fun fetchPokemonList(){
        viewModelScope.launch {
            try{
                _status.value = ApiResponseStatus.LOADING
                _pokeList.value = repository.fetchPokemon(offset.value!!, limit.value!!)
                _status.value = ApiResponseStatus.DONE
            }catch (error:UnknownHostException){
                _status.value = ApiResponseStatus.ERROR
                Log.d(TAG, "No internet Connection")
            }


        }
    }
    fun fetchPokemonListByDataBase(){
        viewModelScope.launch {
            Log.d("ViewModel","Calling viewmodel method fetchPokemonByDatabase")
            _pokeList.value = repository.fetchPokemonByDatabase(offset.value!!, limit.value!!)
            if(_pokeList.value!!.isEmpty()){
                fetchPokemonList()
            }
        }
    }

    fun setOffsetLimit(offset: Int, limit: Int){
        _offset.value = offset
        _limit.value = limit

        Log.d("Offset", _offset.value.toString())
        Log.d("Limit", _limit.value.toString())

    }
    fun setCurrentId(id:Long)
    {
        _currentId.value = id
    }
    fun setCurrentPokemon(pokemon: Pokemon){
        _currentPokemon.value = pokemon
    }
    fun nextPokemon(){

        viewModelScope.launch {
            val nextId = _currentId.value!! + 1
            Log.d("Next Id", nextId.toString())
            _currentPokemon.value = repository.getPokemon(nextId)
            setCurrentId(nextId )
        }
    }
    fun previousPokemon(){

        viewModelScope.launch {
            val previousId = _currentId.value!! - 1
            _currentPokemon.value = repository.getPokemon(previousId)
            setCurrentId(previousId )

        }
    }
}