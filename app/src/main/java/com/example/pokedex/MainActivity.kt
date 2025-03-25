package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
   // private lateinit var detailsFragment: DetailsFragment
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //detailsFragment = supportFragmentManager.findFragmentById(R.id.detail_fragment) as DetailsFragment

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
        val intent = Intent(this,ListGenPokemonActivity::class.java)
        intent.putExtra(ListGenPokemonActivity.OPTION_KEY,opt)
        startActivity(intent)

    }


}