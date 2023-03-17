package com.challenge.pokedex.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.challenge.pokedex.databinding.ActivityPokemonDetailBinding
import com.challenge.pokedex.ui.util.ExtraKeys
import com.google.android.material.tabs.TabLayoutMediator
import com.challenge.pokedex.R
import com.challenge.pokedex.data.datasource.network.util.ImageBuilderUtil
import com.challenge.pokedex.ui.adapter.PokemonDetailPagerAdapter
import com.challenge.pokedex.ui.extensions.loadUrl

class PokemonDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPokemonDetailBinding
    private lateinit var pagerAdapter: PokemonDetailPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        intent.getStringExtra(ExtraKeys.EXTRA_POKEMON_ID)?.let {
            binding.ivPokemon.loadUrl(ImageBuilderUtil.buildFullImageUrl(it))
            pagerAdapter = PokemonDetailPagerAdapter(this,it)
            binding.viewPager.adapter = pagerAdapter
            TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
                when(position){
                    0 -> tab.text = getString(R.string.info)
                    1 -> tab.text = getString(R.string.evolutions)
                }
            }.attach()
        }
        intent.getStringExtra(ExtraKeys.EXTRA_POKEMON_NAME)?.let {
            title = it
        }
    }

    companion object{
        fun start(context: Context, id: String, name: String){
            context.startActivity(
                Intent(context, PokemonDetailActivity::class.java).apply {
                    putExtra(ExtraKeys.EXTRA_POKEMON_ID, id)
                    putExtra(ExtraKeys.EXTRA_POKEMON_NAME, name)
                }
            )
        }
    }
}