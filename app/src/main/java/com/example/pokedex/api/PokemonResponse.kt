package com.example.pokedex.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true )
class PokemonResponse(val id: Long, val name: String, val stats: List<PokemonStat>, val types:List<PokemonType>, val sprites:Sprites, val cries: Cries) {
}