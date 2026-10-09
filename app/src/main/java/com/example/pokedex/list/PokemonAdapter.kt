package com.example.pokedex.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.api.Pokemon
import com.example.pokedex.databinding.PokemonListBinding

class PokemonAdapter: ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(DiffCallBack) {
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
                Pokemon.PokemonType.WATER -> binding.pokemonTypeImage.setImageResource(R.drawable.water_icon)
                Pokemon.PokemonType.FIRE -> binding.pokemonTypeImage.setImageResource(R.drawable.fire_icon)
                Pokemon.PokemonType.FIGHTING -> binding.pokemonTypeImage.setImageResource(R.drawable.fight_icon)
                Pokemon.PokemonType.GRASS ->  binding.pokemonTypeImage.setImageResource(R.drawable.grass_icon)
                Pokemon.PokemonType.ELECTRIC -> binding.pokemonTypeImage.setImageResource(R.drawable.electric_icon)
                Pokemon.PokemonType.NORMAL -> binding.pokemonTypeImage.setImageResource(R.drawable.normal_type)
                Pokemon.PokemonType.ICE -> binding.pokemonTypeImage.setImageResource(R.drawable.ice_type)
                Pokemon.PokemonType.POISON -> binding.pokemonTypeImage.setImageResource(R.drawable.poison_type)
                Pokemon.PokemonType.GROUND -> binding.pokemonTypeImage.setImageResource(R.drawable.ground_type)
                Pokemon.PokemonType.FLYING -> binding.pokemonTypeImage.setImageResource(R.drawable.flying_type)
                Pokemon.PokemonType.PSYCHIC -> binding.pokemonTypeImage.setImageResource(R.drawable.psychic_type)
                Pokemon.PokemonType.BUG -> binding.pokemonTypeImage.setImageResource(R.drawable.bug_type)
                Pokemon.PokemonType.ROCK -> binding.pokemonTypeImage.setImageResource(R.drawable.rock_type)
                Pokemon.PokemonType.GHOST -> binding.pokemonTypeImage.setImageResource(R.drawable.ghost_type)
                Pokemon.PokemonType.DARK -> binding.pokemonTypeImage.setImageResource(R.drawable.dark_type)
                Pokemon.PokemonType.DRAGON -> binding.pokemonTypeImage.setImageResource(R.drawable.dragon_type)
                Pokemon.PokemonType.STEEL -> binding.pokemonTypeImage.setImageResource(R.drawable.steel_type)
                Pokemon.PokemonType.FAIRY -> binding.pokemonTypeImage.setImageResource(R.drawable.fairy_type)
                Pokemon.PokemonType.UNKNOWN -> binding.pokemonTypeImage.setImageResource(R.drawable.unkwon_type)
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
