package com.example.pokedex

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ListFragment.PokemonSelectedListener {
   // private lateinit var detailsFragment: DetailsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //detailsFragment = supportFragmentManager.findFragmentById(R.id.detail_fragment) as DetailsFragment



    }

    override fun onPokemonSelected(pokemon: Pokemon) {
        //detailsFragment.setPokemonData(pokemon)
        findNavController(R.id.main_nav_graph).navigate(ListFragmentDirections.actionListFragmentToPokemonDetailFragment(pokemon))

    }
}