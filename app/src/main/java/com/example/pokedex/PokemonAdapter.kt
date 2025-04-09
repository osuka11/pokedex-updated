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
                Pokemon.Type.NORMAL -> binding.pokemonTypeImage.setImageResource(R.drawable.normal_type)
                Pokemon.Type.ICE -> binding.pokemonTypeImage.setImageResource(R.drawable.ice_type)
                Pokemon.Type.POISON -> binding.pokemonTypeImage.setImageResource(R.drawable.poison_type)
                Pokemon.Type.GROUND -> binding.pokemonTypeImage.setImageResource(R.drawable.ground_type)
                Pokemon.Type.FLYING -> binding.pokemonTypeImage.setImageResource(R.drawable.flying_type)
                Pokemon.Type.PSYCHIC -> binding.pokemonTypeImage.setImageResource(R.drawable.psychic_type)
                Pokemon.Type.BUG -> binding.pokemonTypeImage.setImageResource(R.drawable.bug_type)
                Pokemon.Type.ROCK -> binding.pokemonTypeImage.setImageResource(R.drawable.rock_type)
                Pokemon.Type.GHOST -> binding.pokemonTypeImage.setImageResource(R.drawable.ghost_type)
                Pokemon.Type.DARK -> binding.pokemonTypeImage.setImageResource(R.drawable.dark_type)
                Pokemon.Type.DRAGON -> binding.pokemonTypeImage.setImageResource(R.drawable.dragon_type)
                Pokemon.Type.STEEL -> binding.pokemonTypeImage.setImageResource(R.drawable.steel_type)
                Pokemon.Type.FAIRY -> binding.pokemonTypeImage.setImageResource(R.drawable.fairy_type)
                Pokemon.Type.UNKNOWN -> binding.pokemonTypeImage.setImageResource(R.drawable.unkwon_type)
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
