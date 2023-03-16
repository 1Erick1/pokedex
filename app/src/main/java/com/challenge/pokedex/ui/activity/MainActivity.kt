package com.challenge.pokedex.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
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
            adapter.setItems(listOf())
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        setupSearch((menu.findItem(R.id.search).actionView as SearchView))
        menu.findItem(R.id.search).setOnActionExpandListener(object :
            MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem): Boolean {
                viewModel.fetchAllPokemon()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupSearch(searchView: SearchView){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.length >= QUERY_MIN_LENGTH){
                        viewModel.searchByName(it)
                    }
                }
                return true
            }
        })
    }

    private fun goToDetail(pokemon: PokemonResultModel){

    }

    companion object{
        const val QUERY_MIN_LENGTH = 2
    }
}