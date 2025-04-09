package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.list.ListGenPokemonActivity

class MainActivity : AppCompatActivity(){
   // private lateinit var detailsFragment: DetailsFragment
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //detailsFragment = supportFragmentManager.findFragmentById(R.id.detail_fragment) as DetailsFragment

        //viewModel = ViewModelProvider(this)[MainViewModel::class.java]

       setupBindingOptionCard()


    }

    private fun setupBindingOptionCard() {
        binding.firstGen.setOnClickListener {
            openListActivity(1, 0,150)
        }
        binding.secondGen.setOnClickListener {
            openListActivity(2, 151,251 )

        }
        binding.thirdGen.setOnClickListener {
            openListActivity(3, 252, 386)

        }
        binding.fourthGen.setOnClickListener {
            openListActivity(4, 387, 493)

        }
        binding.FifthGen.setOnClickListener {
            openListActivity(5, 494, 649)

        }
        binding.SixthGen.setOnClickListener {
            openListActivity(6,650 , 721)

        }
        binding.sevenGen.setOnClickListener {
            openListActivity(7, 722, 809)
        }
        binding.EigthGen.setOnClickListener {
            openListActivity(8, 810, 905)

        }

    }

    private fun openListActivity(opt: Int, offset: Int, limit: Int) {
        val intent = Intent(this, ListGenPokemonActivity::class.java)
        intent.putExtra(ListGenPokemonActivity.OPTION_KEY,opt)
        intent.putExtra(ListGenPokemonActivity.OFFSET_KEY,offset)
        intent.putExtra(ListGenPokemonActivity.LIMIT_KEY,limit)
        startActivity(intent)

    }


}