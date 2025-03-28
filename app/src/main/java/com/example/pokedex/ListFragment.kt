package com.example.pokedex

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.FragmentListBinding


class ListFragment : Fragment() {
    interface PokemonSelectedListener{
        fun onPokemonSelected(pokemon: Pokemon)
    }
    private lateinit var pokemonSelectedListener: PokemonSelectedListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        pokemonSelectedListener = try {
            context as PokemonSelectedListener
        }catch (e: java.lang.ClassCastException){
            throw ClassCastException("$context must implement PokemonSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  FragmentListBinding.inflate(inflater)
        val adapter = PokemonAdapter()


        val recycler = view.pokemonRecycler
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        recycler.adapter = adapter

        adapter.onItemClickListener = {
            pokemonSelectedListener.onPokemonSelected(it)
        }
        val pokemonList = mutableListOf(
            Pokemon(1, "Bulbasaur", 45, 49,
                49, 45, Pokemon.PokemonType.GRASS, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.bulbagarden.net%2Fupload%2Fthumb%2F1%2F19%2FAsh_Bulbasaur.png%2F1200px-Ash_Bulbasaur.png&f=1&nofb=1", R.raw.bulbasaur
            ),
            Pokemon(
                2, "Ivysaur", 60, 62,
                63, 60,  Pokemon.PokemonType.GRASS, "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fimg4.wikia.nocookie.net%2F__cb20140612023052%2Fpokemon%2Fimages%2F9%2F90%2FJimmy_Ivysaur.png&f=1&nofb=1", R.raw.ivysaur
            ),
            Pokemon(
                3, "Venuasaur", 80, 82,
                83, 80, Pokemon.PokemonType.GRASS, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fvignette3.wikia.nocookie.net%2Fpokemon%2Fimages%2F5%2F5e%2FBattle_Park_Venusaur.png%2Frevision%2Flatest%3Fcb%3D20151217063847&f=1&nofb=1", R.raw.venusaur
            ),
            Pokemon(
                4, "Charmander", 39, 52,
                43, 65, Pokemon.PokemonType.FIRE, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.ytimg.com%2Fvi%2FdP_AHHZxzAw%2Fmaxresdefault.jpg&f=1&nofb=1", R.raw.charmander
            ),
            Pokemon(
                5, "Charmeleon", 58, 64,
                58, 80, Pokemon.PokemonType.FIRE, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fvignette.wikia.nocookie.net%2Fpoohadventures%2Fimages%2F0%2F06%2F800px-Ash_Charmeleon.png%2Frevision%2Flatest%3Fcb%3D20130815214805&f=1&nofb=1", R.raw.charmeleon
            ),
            Pokemon(
                6, "Charizzard", 78, 84,
                78, 100, Pokemon.PokemonType.FIRE, "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fvignette4.wikia.nocookie.net%2Fpokemon%2Fimages%2Fa%2Fa9%2FAlain%27s_Charizard.png%2Frevision%2Flatest%3Fcb%3D20140404050229&f=1&nofb=1", R.raw.charizard
            ),
            Pokemon(
                7, "Squirtle", 44, 48,
                65, 43, Pokemon.PokemonType.WATER, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwallpapercave.com%2Fwp%2FG8CE8Gu.jpg&f=1&nofb=1", R.raw.squirtle
            ),
            Pokemon(
                8, "Wartortle", 59, 63,
                80, 58, Pokemon.PokemonType.WATER, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.bulbagarden.net%2Fupload%2F9%2F9a%2FMay_Wartortle.png&f=1&nofb=1", R.raw.wartortle
            ),
            Pokemon(
                9, "Blastoise", 79, 83,
                100, 78, Pokemon.PokemonType.WATER, "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fvignette3.wikia.nocookie.net%2Fpokemon%2Fimages%2F0%2F00%2FBlue_Blastoise_PO.png%2Frevision%2Flatest%3Fcb%3D20151010081320&f=1&nofb=1", R.raw.blastoise
            ),
            Pokemon(
                25, "Pikachu", 35, 55,
                40, 90, Pokemon.PokemonType.ELECTRIC, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.ytimg.com%2Fvi%2FZx2CBt72Ji8%2Fmaxresdefault.jpg&f=1&nofb=1", R.raw.pikachu
            ),
            Pokemon(
                26, "Raichu", 60, 90,
                55, 110, Pokemon.PokemonType.ELECTRIC, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.ytimg.com%2Fvi%2FQS7NmgY2tM8%2Fmaxresdefault.jpg&f=1&nofb=1", R.raw.raichu
            ),
        )

        adapter.submitList(pokemonList)

        return view.root
    }


}