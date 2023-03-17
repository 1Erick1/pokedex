package com.challenge.pokedex.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.challenge.pokedex.ui.fragment.PokemonEvolutionsFragment
import com.challenge.pokedex.ui.fragment.PokemonInfoFragment

class PokemonDetailPagerAdapter(activity: AppCompatActivity, private val pokemonId: String): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> PokemonInfoFragment.newInstance(pokemonId)
            1-> PokemonEvolutionsFragment.newInstance(pokemonId)
            else -> {Fragment()}
        }
    }
}