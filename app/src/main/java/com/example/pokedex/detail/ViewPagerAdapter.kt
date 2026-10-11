package com.example.pokedex.detail

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex.R
import com.example.pokedex.Utils.Util
import com.example.pokedex.api.Pokemon
import com.example.pokedex.databinding.ItemPokemonDetailBinding

class ViewPagerAdapter: ListAdapter<Pokemon, ViewPagerAdapter.ViewPagerViewHolder>(
Util.DiffCallBack
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = ItemPokemonDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewPagerViewHolder,
        position: Int
    ) {
        setPokemonData(getItem(position),holder.view)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class ViewPagerViewHolder(val view: ItemPokemonDetailBinding): RecyclerView.ViewHolder(view.root)


    private fun setPokemonData(pokemon: Pokemon,binding: ItemPokemonDetailBinding){
        binding.loadingWheel.visibility = View.VISIBLE
        val  imageView = binding.fragmentDetailImage
        val context = binding.root.context

        Glide.with(binding.fragmentDetailImage  ).load(pokemon.imageUrl).listener(object : RequestListener<Drawable> {


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
                p2: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                binding.loadingWheel.visibility = View.GONE   //ProgressBar gone
                return false            }


        }).into(imageView)

        binding.fragmentDetailHp.text = context.getString(R.string.hp_format,pokemon.hp)
        binding.fragmentDetailAttack.text = context.getString(R.string.attack_format,pokemon.attack)
        binding.fragmentDetailSpeed.text = context.getString(R.string.speed_format,pokemon.speed)
        binding.fragmentDetailDefense.text = context.getString(R.string.defense_format,pokemon.defense)
        binding.detailToolbar.title = pokemon.name





    }

}