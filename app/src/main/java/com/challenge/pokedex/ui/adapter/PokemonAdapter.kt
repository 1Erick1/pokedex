package com.challenge.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.pokedex.databinding.ItemPokemonBinding
import com.challenge.pokedex.ui.extensions.load
import com.challenge.pokedex.ui.model.PokemonResultModel

class PokemonAdapter(private val onClick: (PokemonResultModel) -> Unit): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private val list = mutableListOf<PokemonResultModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun setItems(items: List<PokemonResultModel>){
        list.clear()
        list.addAll(items)
        notifyItemRangeChanged(0,items.size)
    }

    inner class ViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION){
                    onClick(list[adapterPosition])
                }
            }
        }

        fun bindView(pokemon: PokemonResultModel){
            with(binding){
                tvName.text = pokemon.name
                ivPokemon.load(pokemon.thumbnailUrl)
            }
        }
    }
}