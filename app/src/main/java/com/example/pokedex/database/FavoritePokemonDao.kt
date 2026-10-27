package com.example.pokedex.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.api.FavoritePokemon
import com.example.pokedex.api.Pokemon

@Dao
interface FavoritePokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavoritePokemon(favoritePokemon: FavoritePokemon)

    @Delete()
    fun deleteFavoritePokemon(favoritePokemon: FavoritePokemon)

    @Query("SELECT id,name,hp,speed,attack,defense,type,imageUrl,sound FROM pokemon INNER JOIN favorite_pokemon ON id = pokemonId")
    fun getAllFavoritePokemon(): LiveData<List<Pokemon>>
}
