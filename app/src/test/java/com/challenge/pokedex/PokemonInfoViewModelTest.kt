package com.challenge.pokedex

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.pokedex.domain.interactor.GetPokemonDetailInteractor
import com.challenge.pokedex.domain.repository.PokedexRepository
import com.challenge.pokedex.fake.FakePokedexErrorRepository
import com.challenge.pokedex.fake.FakePokedexSuccessRepository
import com.challenge.pokedex.ui.model.PokemonDetailModel
import com.challenge.pokedex.ui.viewmodel.PokemonInfoViewModel
import com.challenge.pokedex.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonInfoViewModelTest {

    private lateinit var viewModel: PokemonInfoViewModel

    @Mock
    private lateinit var pokemonObserver: Observer<PokemonDetailModel>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<Throwable>

    private val successRepository = FakePokedexSuccessRepository()
    private val errorRepository = FakePokedexErrorRepository()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `get pokemon info SUCCESS`(){
        runTest {
            setupViewModel(successRepository)
            viewModel.getPokemonInfo("1")
            Assert.assertNotNull(viewModel.pokemon.value)
            Assert.assertNull(viewModel.error.value)
            Assert.assertFalse(viewModel.progress.value?:false)
        }
    }

    @Test
    fun `get pokemon info ERROR`(){
        runTest {
            setupViewModel(errorRepository)
            viewModel.getPokemonInfo("1")
            Assert.assertNull(viewModel.pokemon.value)
            Assert.assertNotNull(viewModel.error.value)
            Assert.assertFalse(viewModel.progress.value?:false)
        }
    }

    private fun setupViewModel(repository: PokedexRepository){
        viewModel = PokemonInfoViewModel(
            GetPokemonDetailInteractor(repository)
        )
        viewModel.pokemon.observeForever(pokemonObserver)
        viewModel.progress.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)
    }

}