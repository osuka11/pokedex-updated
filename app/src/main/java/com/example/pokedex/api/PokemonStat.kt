package com.example.pokedex.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PokemonStat(val base_stat: Int, val stat:StatInfo) {

}

@JsonClass(generateAdapter = true)
class StatInfo(val name:String ) {

}
