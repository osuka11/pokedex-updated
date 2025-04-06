package com.example.pokedex.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(val id: Long,
                           val name: String, val stats: List<PokemonStat>,
                           val types: List<PokemonType>,
                           val sprites:Sprites) {
}