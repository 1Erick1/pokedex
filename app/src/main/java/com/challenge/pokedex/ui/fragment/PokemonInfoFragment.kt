package com.challenge.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.challenge.pokedex.databinding.FragmentPokemonInfoBinding
import com.challenge.pokedex.ui.adapter.PokemonTypeAdapter
import com.challenge.pokedex.ui.extensions.showToast
import com.challenge.pokedex.ui.model.PokemonDetailModel
import com.challenge.pokedex.ui.util.ExtraKeys
import com.challenge.pokedex.ui.viewmodel.PokemonInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.challenge.pokedex.R

class PokemonInfoFragment: Fragment(){
    private lateinit var binding: FragmentPokemonInfoBinding
    private val typeAdapter = PokemonTypeAdapter()
    private val viewModel: PokemonInfoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
        arguments?.getString(ExtraKeys.EXTRA_POKEMON_ID)?.let {
            viewModel.getPokemonInfo(it)
        }
    }

    private fun setupViewModel() {
        viewModel.pokemon.observe(viewLifecycleOwner){
            showInfo(it)
        }
        viewModel.progress.observe(viewLifecycleOwner){
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner){
            requireActivity().showToast(getString(R.string.generic_error_msg))
        }
    }

    private fun showInfo(pokemon: PokemonDetailModel) {
        with(binding){
            viewContent.visibility = View.VISIBLE
            typeAdapter.setItems(pokemon.types)
            tvHeight.text = pokemon.height
            tvWeight.text = pokemon.weight
            tvAbilities.text = pokemon.abilities
            tvMoves.text = pokemon.moves
        }
    }

    private fun setupView() {
        with(binding){
            rvTypes.adapter = typeAdapter
        }
    }

    companion object{
        fun newInstance(id: String): PokemonInfoFragment{
            val args = Bundle().apply {
                putString(ExtraKeys.EXTRA_POKEMON_ID, id)
            }
            return PokemonInfoFragment().apply {
                arguments = args
            }
        }
    }
}