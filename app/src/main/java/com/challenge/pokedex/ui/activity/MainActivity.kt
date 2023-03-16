package com.challenge.pokedex.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.challenge.pokedex.databinding.ActivityMainBinding
import com.challenge.pokedex.R
import com.challenge.pokedex.ui.adapter.PokemonAdapter
import com.challenge.pokedex.ui.extensions.showToast
import com.challenge.pokedex.ui.model.PokemonResultModel
import com.challenge.pokedex.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val adapter = PokemonAdapter(::goToDetail)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.pokemons.observe(this){
            adapter.setItems(it)
        }
        viewModel.empty.observe(this){
            showToast(getString(R.string.no_results_msg))
        }
        viewModel.error.observe(this){
            showToast(getString(R.string.generic_error_msg))
        }
        viewModel.progress.observe(this){
            binding.swipeRefresh.isRefreshing = it
        }
        viewModel.fetchAllPokemon()
    }

    private fun setupView() {
        with(binding){
            rvPokemon.adapter = adapter
            swipeRefresh.isEnabled = false
        }
    }

    private fun goToDetail(pokemon: PokemonResultModel){

    }
}