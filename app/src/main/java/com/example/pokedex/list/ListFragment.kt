package com.example.pokedex.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.PokemonAdapter
import com.example.pokedex.api.ApiResponseStatus
import com.example.pokedex.api.Pokemon
import com.example.pokedex.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private val args by navArgs<ListFragmentArgs>()

    interface PokemonSelectedListener{
        fun onPokemonSelected(pokemon: Pokemon)
    }
    private lateinit var pokemonSelectedListener: PokemonSelectedListener

    private lateinit var viewModel: ListGenViewModel

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
    ): View {
        // Inflate the layout for this fragment

        val view =  FragmentListBinding.inflate(inflater)
        /*
        val offset = arguments?.getInt("offset")?: 0
        val limit = arguments?.getInt("limit") ?: 0

         */
        val offset = args.offset
        val limit = args.limit

        Log.d("I am Here",offset.toString())
        Log.d("I am Here",limit.toString())


        viewModel = ViewModelProvider(this,ListGenViewModelFactory(requireActivity(), offset,limit))[ListGenViewModel::class.java]

        val adapter = PokemonAdapter()

        val recycler = view.pokemonRecycler
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        recycler.adapter = adapter

        viewModel.pokemonList.observe(viewLifecycleOwner) { pokemonList ->
            adapter.submitList(pokemonList)
            handleEmptyView(pokemonList,view)

        }
        viewModel.status.observe(viewLifecycleOwner) {
                apiResponseStatus ->
            when (apiResponseStatus) {
                ApiResponseStatus.LOADING -> view.progressBar.visibility = View.VISIBLE
                ApiResponseStatus.DONE -> view.progressBar.visibility = View.GONE
                ApiResponseStatus.ERROR -> view.progressBar.visibility = View.GONE
            }
        }

        adapter.onItemClickListener = {
            pokemonSelectedListener.onPokemonSelected(it)
        }

        return view.root
    }
    private fun handleEmptyView(eqList: MutableList<Pokemon>, binding: FragmentListBinding) {
        if (eqList.isEmpty()) {
            binding.emptyView.visibility = View.VISIBLE
        } else {
            binding.emptyView.visibility = View.GONE

        }
    }


}