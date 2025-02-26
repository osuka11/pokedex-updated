package com.example.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.FragmentListBinding


class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  FragmentListBinding.inflate(inflater)

        val recycler = view.pokemonRecycler
        recycler.layoutManager = LinearLayoutManager(requireActivity())

        return view.root
    }


}