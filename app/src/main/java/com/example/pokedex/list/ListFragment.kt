package com.example.pokedex.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.api.ApiResponseStatus
import com.example.pokedex.api.Pokemon
import com.example.pokedex.databinding.FragmentListBinding
import com.example.pokedex.main.MainViewModel
import com.example.pokedex.main.MainViewModelFactory


class ListFragment : Fragment() {
    interface PokemonSelectedListener{
        fun onPokemonSelected(pokemon: Pokemon)
    }
    private val integerArgs: ListFragmentArgs by navArgs()

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(
            requireActivity().application
        )
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

        viewModel.pokeList.observe(viewLifecycleOwner, Observer{
                pokeList ->   adapter.submitList(pokeList)

        })
        viewModel.offset.observe(viewLifecycleOwner, Observer{
            viewModel.fetchPokemonListByDataBase()
        })

        viewModel.status.observe(viewLifecycleOwner){
                apiResponseStatus ->
            when(apiResponseStatus){
                ApiResponseStatus.LOADING -> view.progressBar.visibility = View.VISIBLE

                ApiResponseStatus.DONE -> view.progressBar.visibility = View.GONE

                ApiResponseStatus.ERROR -> view.progressBar.visibility = View.GONE

            }
        }
        /*viewModel.currentPosition.observe(viewLifecycleOwner){
            it ->
            println(it)
        }

         */



        val recycler = view.pokemonRecycler
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        recycler.adapter = adapter

        adapter.onItemClickListener = {
            pokemonSelectedListener.onPokemonSelected(it)

            viewModel.setCurrentId(it.id)
            viewModel.setCurrentPokemon(it)

            Log.d("List Fragment", viewModel.currentId.value.toString())
            Log.d("List Fragment", viewModel.currentPokemon.value!!.name)
        }


        return view.root
    }



}