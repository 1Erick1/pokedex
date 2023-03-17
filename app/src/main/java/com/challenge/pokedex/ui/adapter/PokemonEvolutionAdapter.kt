package com.challenge.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.pokedex.databinding.ItemPokemonEvolutionBinding
import com.challenge.pokedex.ui.model.EvolutionModel
import com.challenge.pokedex.ui.extensions.loadUrl
import android.view.View

class PokemonEvolutionAdapter: RecyclerView.Adapter<PokemonEvolutionAdapter.ViewHolder>() {
    private val list = mutableListOf<EvolutionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPokemonEvolutionBinding.inflate(
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
        holder.bindView(list[position], position==list.size-1)
    }

    fun setItems(items: List<EvolutionModel>){
        list.clear()
        list.addAll(items)
        notifyItemRangeInserted(0,items.size)
    }

    class ViewHolder(private val binding: ItemPokemonEvolutionBinding): RecyclerView.ViewHolder(binding.root){

        fun bindView(evolution: EvolutionModel, isLast: Boolean){
            with(binding){
                ivPokemon.loadUrl(evolution.imageUrl)
                tvName.text = evolution.name
                ivArrow.visibility = if (isLast) View.GONE else View.VISIBLE
            }
        }
    }
}