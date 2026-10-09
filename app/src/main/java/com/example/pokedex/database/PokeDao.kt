package com.example.pokedex.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.api.Pokemon

@Dao
interface PokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemonList: MutableList<Pokemon>)

    @Query("SELECT * FROM pokemon WHERE id BETWEEN :offset AND :limit")
    fun getPokemonGeneration(offset: Int, limit:Int): MutableList<Pokemon>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemon(id:Long): Pokemon
}