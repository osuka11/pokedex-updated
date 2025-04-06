package com.example.pokedex.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class PokemonType(@Json(name = "type")val typeInfo:TypeInfo) {

}

@JsonClass(generateAdapter = true)
class TypeInfo(val name: String) {

}
