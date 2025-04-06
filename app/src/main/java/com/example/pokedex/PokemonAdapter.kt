package com.example.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.api.Pokemon
import com.example.pokedex.databinding.PokemonListBinding

class PokemonAdapter:ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(DiffCallBack) {
    companion object DiffCallBack: DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

    }
    lateinit var onItemClickListener: (pokemon: Pokemon) -> Unit
    inner class ViewHolder(private val binding: PokemonListBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(pokemon: Pokemon){
            binding.pokemonId.text = pokemon.id.toInt().toString()
            binding.pokemonName.text = pokemon.name
            when(pokemon.type){

                Pokemon.Type.WATER -> binding.pokemonTypeImage.setImageResource(R.drawable.water_icon)
                Pokemon.Type.FIRE -> binding.pokemonTypeImage.setImageResource(R.drawable.fire_icon)
                Pokemon.Type.FIGHTING -> binding.pokemonTypeImage.setImageResource(R.drawable.fight_icon)
                Pokemon.Type.GRASS ->  binding.pokemonTypeImage.setImageResource(R.drawable.grass_icon)
                Pokemon.Type.ELECTRIC -> binding.pokemonTypeImage.setImageResource(R.drawable.electric_icon)
                Pokemon.Type.NORMAL -> TODO()
                Pokemon.Type.ICE -> TODO()
                Pokemon.Type.POISON -> TODO()
                Pokemon.Type.GROUND -> TODO()
                Pokemon.Type.FLYING -> TODO()
                Pokemon.Type.PSYCHIC -> TODO()
                Pokemon.Type.BUG -> TODO()
                Pokemon.Type.ROCK -> TODO()
                Pokemon.Type.GHOST -> TODO()
                Pokemon.Type.DARK -> TODO()
                Pokemon.Type.DRAGON -> TODO()
                Pokemon.Type.STEEL -> TODO()
                Pokemon.Type.FAIRY -> TODO()
                Pokemon.Type.UNKNOWN -> TODO()
            }
            binding.root.setOnClickListener {
                if(::onItemClickListener.isInitialized){
                    onItemClickListener(pokemon)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PokemonListBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }


}
