package com.challenge.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.challenge.pokedex.databinding.ItemPokemonTypeBinding
import com.challenge.pokedex.domain.entity.Type
import com.challenge.pokedex.ui.extensions.capitalizeFirst
import com.challenge.pokedex.ui.util.ColorUtils

class PokemonTypeAdapter(): RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder>() {
    private val list = mutableListOf<Type>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPokemonTypeBinding.inflate(
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

    fun setItems(items: List<Type>){
        list.clear()
        list.addAll(items)
        notifyItemRangeInserted(0,items.size)
    }

    class ViewHolder(private val binding: ItemPokemonTypeBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindView(type: Type){
            binding.cvContainer.setCardBackgroundColor(ContextCompat.getColor(itemView.context,ColorUtils.getTypeColor(type.name)))
            binding.tvType.text = type.name.capitalizeFirst()
        }
    }
}