package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.detail.ListGenPokemonActivity

class MainActivity : AppCompatActivity(){
   // private lateinit var detailsFragment: DetailsFragment
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //detailsFragment = supportFragmentManager.findFragmentById(R.id.detail_fragment) as DetailsFragment

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

       setupBindingOptionCard()


    }

    private fun setupBindingOptionCard() {
        binding.firstGen.setOnClickListener {
            openListActivity(1)
        }
        binding.secondGen.setOnClickListener {
            openListActivity(2)

        }
        binding.thirdGen.setOnClickListener {
            openListActivity(3)

        }
        binding.fourthGen.setOnClickListener {
            openListActivity(4)

        }
        binding.FifthGen.setOnClickListener {
            openListActivity(5)

        }
        binding.SixthGen.setOnClickListener {
            openListActivity(6)

        }
        binding.sevenGen.setOnClickListener {
            openListActivity(7)
        }
        binding.EigthGen.setOnClickListener {
            openListActivity(8)

        }

    }

    private fun openListActivity(opt: Int) {
        val intent = Intent(this, ListGenPokemonActivity::class.java)
        intent.putExtra(ListGenPokemonActivity.OPTION_KEY,opt)
        startActivity(intent)

    }


}