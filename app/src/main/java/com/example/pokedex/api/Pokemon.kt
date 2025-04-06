package com.example.pokedex.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(val id: Long,
                   val name: String,
                   val hp:Int,
                   val speed: Int,
                   val attack: Int,
                   val defense: Int,
                   val type: Type,
                   val imageUrl:String,
                   val sound: Int):Parcelable {


    enum class Type{
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