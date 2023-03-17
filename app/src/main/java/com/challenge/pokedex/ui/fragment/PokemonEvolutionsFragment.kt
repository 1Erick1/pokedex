package com.challenge.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.challenge.pokedex.databinding.FragmentPokemonEvolutionsBinding
import com.challenge.pokedex.ui.adapter.PokemonEvolutionAdapter
import com.challenge.pokedex.ui.util.ExtraKeys
import com.challenge.pokedex.ui.viewmodel.EvolutionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonEvolutionsFragment: Fragment() {
    private lateinit var binding: FragmentPokemonEvolutionsBinding
    private val viewModel: EvolutionsViewModel by viewModel()
    private val adapter = PokemonEvolutionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonEvolutionsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.evolutions.observe(viewLifecycleOwner){
            adapter.setItems(it)
        }
        viewModel.progress.observe(viewLifecycleOwner){
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.noEvolutions.observe(viewLifecycleOwner){
            binding.tvNoEvolutions.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setupView() {
        binding.rvEvolutions.adapter = adapter
    }

    companion object{
        fun newInstance(id: String): PokemonEvolutionsFragment{
            val args = Bundle().apply {
                putString(ExtraKeys.EXTRA_POKEMON_ID, id)
            }
            return PokemonEvolutionsFragment().apply {
                arguments = args
            }
        }
    }
}