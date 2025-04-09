package com.example.pokedex.list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphNavigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pokedex.R
import com.example.pokedex.api.Pokemon
import com.example.pokedex.databinding.ActivityListGenPokemonBinding

class ListGenPokemonActivity : AppCompatActivity(),ListFragment.PokemonSelectedListener {
    companion object{
        const val OPTION_KEY = "option_key"
        const val OFFSET_KEY = "offset_key"
        const val LIMIT_KEY = "limit_key"
    }
    //private lateinit var viewModel: ListGenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListGenPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_container) as NavHostFragment
        val navController = navHostFragment.navController
        val bundle = intent.extras
        val offset = bundle?.getInt(OFFSET_KEY) ?:0
        val limit = bundle?.getInt(LIMIT_KEY) ?: 0

        println(offset)
        println(limit)

        //val args = ListFragmentArgs(offset, limit).toBundle()
        val args = bundleOf("offset" to offset, "limit" to limit)

        navController.setGraph(R.navigation.main_nav_graph, args)



        //listFragment = supportFragmentManager.findFragmentById(R.id.main_nav_container) as ListFragment
        //listFragment.setPokemonList(offset!!,limit!!)




    }

    override fun onPokemonSelected(pokemon: Pokemon) {
        findNavController(R.id.main_nav_container).navigate(ListFragmentDirections.actionListFragmentToPokemonDetailFragment(pokemon))

    }
}