package com.example.pokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.api.Pokemon

@Database(entities = [Pokemon::class], version = 1)
abstract class PokeDatabase:RoomDatabase(){
    abstract val pokeDao: PokeDao
}
private lateinit var INSTANCE: PokeDatabase

fun getDatabase(context: Context): PokeDatabase{
    synchronized(PokeDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PokeDatabase::class.java,
                "pokemon_db"
            ).build()
        }
        return INSTANCE
    }
}