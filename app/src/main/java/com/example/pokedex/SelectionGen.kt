package com.example.pokedex

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.pokedex.databinding.FragmentSelectionGenBinding
import com.example.pokedex.main.MainViewModel
import com.example.pokedex.main.MainViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectionGen.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectionGen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    interface GenSelectedListener{
        fun onGenSelected(offset: Int, limit: Int)
    }

    private lateinit var view: FragmentSelectionGenBinding
    private val viewModel: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    private lateinit var genSelectedListener: GenSelectedListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        genSelectedListener = try {
            context as GenSelectedListener
        }catch (e: java.lang.ClassCastException){
            throw ClassCastException("$context must implement PokemonSelectedListener")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        view = FragmentSelectionGenBinding.inflate(inflater)

        setupBindingOptionCard()
        


        return view.root



    }

    private fun setupBindingOptionCard() {
        view.firstGen.setOnClickListener {
            genSelectedListener.onGenSelected(1,150)
            viewModel.setOffsetLimit(1,150)


        }
        view.secondGen.setOnClickListener {
            genSelectedListener.onGenSelected(152,251)
            viewModel.setOffsetLimit(152,251)



        }
        view.thirdGen.setOnClickListener {
            genSelectedListener.onGenSelected(252,387)
            viewModel.setOffsetLimit(252,387)


        }
        view.fourthGen.setOnClickListener {
            genSelectedListener.onGenSelected(388,494)
            viewModel.setOffsetLimit(388,494)


        }
        view.FifthGen.setOnClickListener {
            genSelectedListener.onGenSelected(495,650)
            viewModel.setOffsetLimit(495,650)


        }
        view.SixthGen.setOnClickListener {
            genSelectedListener.onGenSelected(651,722)
            viewModel.setOffsetLimit(651,722)


        }
        view.sevenGen.setOnClickListener {
            genSelectedListener.onGenSelected(723,809)
            viewModel.setOffsetLimit(723,809)

        }
        view.EigthGen.setOnClickListener {
            genSelectedListener.onGenSelected(810,906)
            viewModel.setOffsetLimit(810,906)


        }


    }





}