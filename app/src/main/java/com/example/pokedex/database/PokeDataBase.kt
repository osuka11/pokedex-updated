package com.example.pokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.api.Pokemon
import kotlin.jvm.java

@Database(entities = [Pokemon::class], version = 1)
abstract class PokeDataBase: RoomDatabase(){
    abstract val pokeDao: PokeDao
}

private lateinit var INSTANCE: PokeDataBase

fun getDatabase(context: Context): PokeDataBase{
    synchronized(PokeDataBase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PokeDataBase::class.java,
                "earthquake_db"
            ).build()
        }
        return INSTANCE
    }
}