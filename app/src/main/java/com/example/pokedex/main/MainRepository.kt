package com.example.pokedex.main

import android.util.Log
import com.example.pokedex.api.Pokemon
import com.example.pokedex.api.PokemonResponse
import com.example.pokedex.api.service
import com.example.pokedex.database.PokeDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(val db: PokeDataBase) {

    //val pokemonList: LiveData<MutableList<Pokemon>> = db.pokeDao.getPokemon()
    fun parsePokemon(pokemonResponse: PokemonResponse): Pokemon {
        val id = pokemonResponse.id
        val name = pokemonResponse.name
        var hp = 0
        var defense = 0
        var attack = 0
        var speed = 0
        for (stat in pokemonResponse.stats) {
            when (stat.stat.name) {
                "hp" -> hp = stat.base_stat
                "defense" -> defense = stat.base_stat
                "attack" -> attack = stat.base_stat
                "speed" -> speed = stat.base_stat
            }
        }
        val typeName = pokemonResponse.types.get(0).typeInfo.name
        val type = mapTypeFromApi(typeName)
        val imageUrl = pokemonResponse.sprites.front_default!!
        val cries = pokemonResponse.cries.latest!!

        return Pokemon(id,name,hp,speed,attack,defense,type,imageUrl,cries)


    }
    suspend fun fetchPokemonByDatabase(offset: Int, limit: Int): MutableList<Pokemon>{
        return withContext(Dispatchers.IO){
            Log.d("I am here","I am here")
            db.pokeDao.getPokemonGeneration(offset,limit)
        }
    }
    suspend fun fetchPokemon(offset:Int, limit: Int): MutableList<Pokemon>{


        return withContext(Dispatchers.IO){
            val pokemonList = mutableListOf<Pokemon>()

            /*
            for (i in offset..limit) {
                val pokemonResponse = service.getPokemon(i)


                pokemonList.add(parsePokemon(pokemonResponse))




            }

             */
            for (i in 1..906){
                val pokemonResponse = service.getPokemon(i)


                pokemonList.add(parsePokemon(pokemonResponse))
            }
            db.pokeDao.insertAll(pokemonList)
            fetchPokemonByDatabase(offset,limit)


        }


    }
    suspend fun getPokemon(id:Long): Pokemon{
        return withContext(Dispatchers.IO){
            db.pokeDao.getPokemon(id)
        }
    }


    fun mapTypeFromApi(typeName: String): Pokemon.PokemonType {
        return when (typeName.lowercase()) {
            "normal" -> Pokemon.PokemonType.NORMAL
            "fire" -> Pokemon.PokemonType.FIRE
            "water" -> Pokemon.PokemonType.WATER
            "grass" -> Pokemon.PokemonType.GRASS
            "electric" -> Pokemon.PokemonType.ELECTRIC
            "ice" -> Pokemon.PokemonType.ICE
            "fighting" -> Pokemon.PokemonType.FIGHTING
            "poison" -> Pokemon.PokemonType.POISON
            "ground" -> Pokemon.PokemonType.GROUND
            "flying" -> Pokemon.PokemonType.FLYING
            "psychic" -> Pokemon.PokemonType.PSYCHIC
            "bug" -> Pokemon.PokemonType.BUG
            "rock" -> Pokemon.PokemonType.ROCK
            "ghost" -> Pokemon.PokemonType.GHOST
            "dark" -> Pokemon.PokemonType.DARK
            "dragon" -> Pokemon.PokemonType.DRAGON
            "steel" -> Pokemon.PokemonType.STEEL
            "fairy" -> Pokemon.PokemonType.FAIRY
            else -> Pokemon.PokemonType.UNKNOWN
        }
    }
}

