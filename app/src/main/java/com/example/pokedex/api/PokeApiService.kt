package com.example.pokedex.api

import com.example.pokedex.Constants
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface PokeApiService{
    @GET(Constants.ALL_POKEMON_LIST) //This is the endpoint
    suspend fun getLastHourEarthquakes(

    ):PokemonResponse

}

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

//val jsonAdapter = moshi.adapter<EqJsonResponse>()

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
var service: PokeApiService = retrofit.create<PokeApiService>(PokeApiService::class.java)