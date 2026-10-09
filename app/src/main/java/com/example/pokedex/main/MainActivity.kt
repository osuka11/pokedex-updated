package com.example.pokedex.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.pokedex.list.ListFragment
import com.example.pokedex.list.ListFragmentDirections
import com.example.pokedex.R
import com.example.pokedex.SelectionGen
import com.example.pokedex.SelectionGenDirections
import com.example.pokedex.api.Pokemon
import com.example.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ListFragment.PokemonSelectedListener, SelectionGen.GenSelectedListener {
    // private lateinit var detailsFragment: DetailsFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //detailsFragment = supportFragmentManager.findFragmentById(R.id.detail_fragment) as DetailsFragment

        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)



    }



    override fun onPokemonSelected(pokemon: Pokemon) {
        findNavController(R.id.main_nav_container).navigate(ListFragmentDirections.actionListFragmentToPokemonDetailFragment(pokemon))
    }

    override fun onGenSelected(offset: Int, limit: Int) {
        findNavController(R.id.main_nav_container).navigate(SelectionGenDirections.actionSelectionGenToListFragment4(offset,limit))
    }
}