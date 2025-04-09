package com.example.pokedex

import com.example.pokedex.api.Pokemon
import com.example.pokedex.api.PokemonList
import com.example.pokedex.api.PokemonResponse
import com.example.pokedex.api.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val tag = MainRepository::class.java.simpleName


class MainRepository {
    private suspend fun fetchPokemonList():MutableList<PokemonList> {
        return withContext(Dispatchers.IO){
            val response = service.getPokemonListUrls()
            response.results
        }

    }

    suspend fun fetchPokemon(offset: Int, limit:Int ): MutableList<Pokemon>{
        return withContext(Dispatchers.IO){
            //val pokemonListResponse = fetchPokemonList()
            val pokemonList = mutableListOf<Pokemon>()

            for (id in offset until limit){
                val pokemonResponse = service.getPokemon(id)
                pokemonList.add(parsePokemonResults(pokemonResponse))
            }
            /*
            for(pokemon in pokemonListResponse){
                val pokemonResponse = service.getPokemon(pokemon.name)
                pokemonList.add(parsePokemonResults(pokemonResponse))

            }

             */


            pokemonList
        }
    }

    private fun parsePokemonResults(
        pokemonResponse: PokemonResponse
    ):Pokemon {
        val id = pokemonResponse.id
        val name = pokemonResponse.name
        var hp: Int = 0
        var speed: Int = 0
        var attack: Int = 0
        var defense: Int = 0
        for(stat in pokemonResponse.stats){
            when(stat.stat.name){
                "hp" -> hp = stat.base_stat
                "speed" ->  speed = stat.base_stat
                "defense" -> defense = stat.base_stat
                "attack" -> attack = stat.base_stat
            }
        }
        val typeName = pokemonResponse.types[0].typeInfo.name
        val type = mapTypeFromApiSafe(typeName)
        val imageUrl = pokemonResponse.sprites.front_default
        return Pokemon(id,name,hp,speed,attack,defense,type,imageUrl!!,1 )
    }

    private fun mapTypeFromApiSafe(typeName: String): Pokemon.Type {
        return try {
            Pokemon.Type.valueOf(typeName.uppercase())
        } catch (e: IllegalArgumentException) {
            Pokemon.Type.UNKNOWN
        }
    }
}