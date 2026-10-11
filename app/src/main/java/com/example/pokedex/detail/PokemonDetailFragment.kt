package com.example.pokedex.detail

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.pokedex.databinding.FragmentPokemonDetailBinding
import com.example.pokedex.main.MainViewModel
import com.example.pokedex.main.MainViewModelFactory
import kotlin.math.abs

class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding

    companion object{
        const val SWIPE_MIN_DISTANCE: Int = 50

        const val SWIPE_THRESHOLD_VELOCITY: Int = 50
    }

    private lateinit var gestureDetector: GestureDetector
    //private val pokemonargs: PokemonDetailFragmentArgs by navArgs()

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(
            requireActivity().application
        )
    }



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

         binding = FragmentPokemonDetailBinding.inflate(inflater)

        val adapter = ViewPagerAdapter()

        val viewPager = binding.viewPager

        viewPager.adapter = adapter

        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        viewModel.pokeList.observe(viewLifecycleOwner, Observer{
            pokeList ->
            adapter.submitList(pokeList)
            val selectedPokemon = viewModel.currentPokemon.value!!

            val index = pokeList.indexOfFirst {
                it.id == selectedPokemon.id
            }
            viewPager.currentItem = index
        })
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                val pokemon = adapter.currentList.getOrNull(position)
                    ?: return

                viewModel.setCurrentPokemon(pokemon)

            }
        })
        viewModel.currentPokemon.observe(viewLifecycleOwner, Observer{
            //todo
        })

        //setPokemonData(pokemonargs.pokemon)

        //setupToolbar(pokemonargs.pokemon.name)
        binding.root.setOnTouchListener { view, event -> gestureDetector.onTouchEvent(event) }
        binding.playButton.setOnClickListener {
            try {
                val mediaPlayer = MediaPlayer.create(requireActivity(),viewModel.currentPokemon.value!!.sound.toUri())
                mediaPlayer.start()
            }catch (e:Exception){
                Log.d("Error audio",e.toString())
                Toast.makeText(requireActivity(),"Audio no Disponible", Toast.LENGTH_SHORT).show()
            }

        }


        return binding.root
    }



    /*
    private fun setupToolbar(pokemonName: String) {
        val toolbar = binding.detailToolbar
        val collapsingToolbar = binding.collapsingToolbar
        collapsingToolbar.title = pokemonName
        toolbar.title = pokemonName
        toolbar.setNavigationIcon(R.drawable.arrow_back_basic_svgrepo_com)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()

        }
    }

     */



}