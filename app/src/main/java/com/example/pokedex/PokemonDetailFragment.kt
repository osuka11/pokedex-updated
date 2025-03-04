package com.example.pokedex

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex.databinding.FragmentPokemonDetailBinding


class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding
    private val pokemonargs: PokemonDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentPokemonDetailBinding.inflate(inflater)

        setPokemonData(pokemonargs.pokemon)
        setupToolbar(pokemonargs.pokemon.name)
        binding.playButton.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(requireActivity(),pokemonargs.pokemon.sound)
            mediaPlayer.start()
        }

        return binding.root
    }

    private fun setupToolbar(pokemonName: String) {
        val toolbar = binding.detailToolbar
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

}