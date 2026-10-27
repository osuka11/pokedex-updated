package com.example.pokedex.detail

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
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
import com.example.pokedex.Utils.Util.isFavorite
import com.example.pokedex.api.Pokemon
import com.example.pokedex.databinding.ItemPokemonDetailBinding
import kotlin.math.log

class ViewPagerAdapter(): ListAdapter<Pokemon, ViewPagerAdapter.ViewPagerViewHolder>(
Util.DiffCallBack
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = ItemPokemonDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       view.detailToolbar.inflateMenu(R.menu.menu_detail)
        return ViewPagerViewHolder(view,parent)
    }

    override fun onBindViewHolder(
        holder: ViewPagerViewHolder,
        position: Int
    ) {
        Log.d("VM","ADAPTER BIND")

        setPokemonData(getItem(position),holder.view, position)

        //setupToolbar(getItem(position).name,holder.view)
    }
    init {

    }

    lateinit var onFavoritePokemonAction: (pokemon: Pokemon) -> Unit
    //lateinit var favoriteIdsList: List<Pokemon>
    private var favoriteIdsList = setOf<Long>()


    override fun getItemCount(): Int {
        return currentList.size
    }

    class ViewPagerViewHolder(val view: ItemPokemonDetailBinding, parent: ViewGroup): RecyclerView.ViewHolder(view.root)


    private fun setPokemonData(pokemon: Pokemon, binding: ItemPokemonDetailBinding, position: Int){
        val favoriteItem = binding.detailToolbar.menu
            .findItem(R.id.favorite_action)
        updateFavoriteIcon(favoriteItem,pokemon,position)
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


        //binding.detailToolbar.inflateMenu(R.menu.menu_detail)
        binding.detailToolbar.title = pokemon.name






       // updateFavoriteIcon(favoriteItem,pokemon,position)

        binding.detailToolbar.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.favorite_action->{
                    if (::onFavoritePokemonAction.isInitialized){
                        onFavoritePokemonAction(pokemon)
                    }


                    true
                }
                else -> false
            }

        }



    }

    private fun setupToolbar(pokemonName: String, binding: ItemPokemonDetailBinding) {
        val toolbar = binding.detailToolbar
        toolbar.title = pokemonName
        toolbar.setNavigationIcon(R.drawable.arrow_back_basic_svgrepo_com)

    }


    private fun updateFavoriteIcon(
        item: MenuItem, pokemon: Pokemon, position: Int
    ) {

        val isFavorite = pokemon.id in favoriteIdsList
        item.setIcon(
            if (isFavorite) {
                //Log.d("VM","favorite ${pokemon.isFavorite(favoriteIdsList)}")

                R.drawable.heart2

            } else {
                //Log.d("VM","favorite ${pokemon.isFavorite(favoriteIdsList)}")

                R.drawable.heart1

            }
        )
    }
    fun updateFavorites(favorites: List<Pokemon>) {
        // Store the IDs and update the affected pages
        val newFavoriteIds = favorites.map { it.id }.toSet()

        val changedIds =
            (favoriteIdsList - newFavoriteIds) +
                    (newFavoriteIds - favoriteIdsList)

        favoriteIdsList = newFavoriteIds

        currentList.forEachIndexed { index, pokemon ->
            if (pokemon.id in changedIds) {
                notifyItemChanged(index)
            }
        }

    }



}