package com.example.pokedex.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Cries( val latest:String?) {
}