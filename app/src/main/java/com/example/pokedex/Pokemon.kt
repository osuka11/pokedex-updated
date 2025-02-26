package com.example.pokedex

data class Pokemon(    val id: Long,
                  val name: String,

                  val hp:Int,
                  val speed: Int,
                  val attack: Int,
                  val defense: Int,
                  val type: PokemonType,
                  val imageUrl: String,
                  val sound: Int) {
    enum class PokemonType{ WATER, FIRE, FIGHT, GRASS, ELECTRIC}

}