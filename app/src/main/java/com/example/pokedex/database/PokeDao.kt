package com.example.pokedex.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.api.Pokemon

@Dao
interface PokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemonList:MutableList<Pokemon>)

    @Query("SELECT * FROM pokemons WHERE id BETWEEN :offset AND :limit")
    fun getAllPokemons(offset: Int, limit:Int):MutableList<Pokemon>
}