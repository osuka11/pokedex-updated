package com.example.pokedex.Utils

import androidx.recyclerview.widget.DiffUtil
import com.example.pokedex.api.Pokemon

object Util {
    object DiffCallBack : DiffUtil.ItemCallback<Pokemon>() {

        override fun areItemsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon
        ) = oldItem == newItem
    }
}