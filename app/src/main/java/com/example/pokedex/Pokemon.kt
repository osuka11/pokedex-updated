package com.example.pokedex

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(    val id: Long,
                  val name: String,
                  val hp:Int,
                  val speed: Int,
                  val attack: Int,
                  val defense: Int,
                  val type: PokemonType,
                  val imageUrl: String,
                  val sound: Int):Parcelable {
    enum class PokemonType{ WATER, FIRE, FIGHT, GRASS, ELECTRIC}

}