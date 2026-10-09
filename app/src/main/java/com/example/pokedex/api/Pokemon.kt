package com.example.pokedex.api

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "pokemon")
data class Pokemon(@PrimaryKey val id: Long,
                   val name: String,
                   val hp:Int,
                   val speed: Int,
                   val attack: Int,
                   val defense: Int,
                   val type: PokemonType,
                   val imageUrl: String,
                   val sound: String):Parcelable {
    enum class PokemonType {
        NORMAL,
        FIRE,
        WATER,
        GRASS,
        ELECTRIC,
        ICE,
        FIGHTING,
        POISON,
        GROUND,
        FLYING,
        PSYCHIC,
        BUG,
        ROCK,
        GHOST,
        DARK,
        DRAGON,
        STEEL,
        FAIRY,
        UNKNOWN
    }

}