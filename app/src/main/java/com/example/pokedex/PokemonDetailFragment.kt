package com.example.pokedex

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex.api.Pokemon
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
    private val pokemonargs: PokemonDetailFragmentArgs by navArgs()

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(
            requireActivity().application
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    private val touchListener = View.OnTouchListener{
        view, event ->
        gestureDetector.onTouchEvent(event)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

         binding = FragmentPokemonDetailBinding.inflate(inflater)
        setGestureDetector()


        setPokemonData(pokemonargs.pokemon)

        setupToolbar(pokemonargs.pokemon.name)
        binding.root.setOnTouchListener { view, event -> gestureDetector.onTouchEvent(event) }
        binding.playButton.setOnClickListener {
            try {
                val mediaPlayer = MediaPlayer.create(requireActivity(),pokemonargs.pokemon.sound.toUri())
                mediaPlayer.start()
            }catch (e:Exception){
                Log.d("Error audio",e.toString())
                Toast.makeText(requireActivity(),"Audio no Disponible",Toast.LENGTH_SHORT).show()
            }

        }
        binding.nextPokemonButton.setOnClickListener {

            nextPokemon()
        }
        viewModel.currentPokemon.observe(viewLifecycleOwner, Observer{
            Log.d("POKEMON_TEST", "Observer Pokemon: ${it.name}")

            setupToolbar(it.name)
            setPokemonData(it)
        })

        return binding.root
    }

    private fun setGestureDetector() {

        gestureDetector = GestureDetector(requireActivity(), object :
            GestureDetector.SimpleOnGestureListener(){
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                val differenceY = (e2.y) - (e1!!.y )

                Log.d("GESTURE", "Start Y: ${e1.y}")
                Log.d("GESTURE", "End Y: ${e2.y}")
                Log.d("GESTURE", "Difference Y: $differenceY")
                Log.d("GESTURE", "Velocity Y: $velocityY")

                if (abs(velocityY) >=SWIPE_THRESHOLD_VELOCITY && abs(
                        differenceY
                    ) >=SWIPE_MIN_DISTANCE ) {
                    if (differenceY < 0 ){
                        viewModel.nextPokemon()
                    }else{
                        viewModel.previousPokemon()
                    }

                }

                return true
            }

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }
            })
    }


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

    private fun setPokemonData(pokemon: Pokemon){
        binding.loadingWheel.visibility = View.VISIBLE
        val  imageView = binding.fragmentDetailImage

        Glide.with(this).load(pokemon.imageUrl).listener(object : RequestListener<Drawable> {


            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                binding.loadingWheel.visibility = View.GONE   //ProgressBar gone
                binding.fragmentDetailImage.setImageResource(R.drawable.descarga)
                return false            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                binding.loadingWheel.visibility = View.GONE   //ProgressBar gone
                return false            }


        }).into(imageView)

        binding.fragmentDetailHp.text = getString(R.string.hp_format,pokemon.hp)
        binding.fragmentDetailAttack.text = getString(R.string.attack_format,pokemon.attack)
        binding.fragmentDetailSpeed.text = getString(R.string.speed_format,pokemon.speed)
        binding.fragmentDetailDefense.text = getString(R.string.defense_format,pokemon.defense)



    }
    fun nextPokemon(){
        viewModel.nextPokemon()


    }


}