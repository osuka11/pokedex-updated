package com.example.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import com.example.pokedex.databinding.ActivityListGenPokemonBinding
import com.example.pokedex.list.ListFragment

class ListGenPokemonActivity : AppCompatActivity(){
    companion object{
        const val OFFSET_KEY = "limit"
        const val LIMIT_KEY = "offset"
        const val OPTION_KEY = "key"
    }
    private lateinit var listFragment: ListFragment
    private lateinit var binding: ActivityListGenPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListGenPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = intent.extras!!
        val option = bundle.getString(OPTION_KEY)
        val offset = bundle.getString(OFFSET_KEY)
        val limit = bundle.getString(LIMIT_KEY)



    }

}