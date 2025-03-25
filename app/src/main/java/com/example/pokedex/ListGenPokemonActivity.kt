package com.example.pokedex

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pokedex.databinding.ActivityListGenPokemonBinding

class ListGenPokemonActivity : AppCompatActivity() {
    companion object{
        const val OPTION_KEY = "option_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListGenPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        binding.optText.text = bundle?.getInt(OPTION_KEY).toString()


    }
}