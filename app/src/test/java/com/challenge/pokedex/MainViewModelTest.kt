package com.challenge.pokedex

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.pokedex.domain.interactor.DownloadThumbnailsInteractor
import com.challenge.pokedex.domain.interactor.FetchAllPokemonInteractor
import com.challenge.pokedex.domain.interactor.SearchPokemonByNameInteractor
import com.challenge.pokedex.domain.repository.PokedexRepository
import com.challenge.pokedex.fake.FakePokedexEmptyRepository
import com.challenge.pokedex.fake.FakePokedexErrorRepository
import com.challenge.pokedex.fake.FakePokedexSuccessRepository
import com.challenge.pokedex.ui.model.PokemonResultModel
import com.challenge.pokedex.ui.viewmodel.MainViewModel
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
class MainViewModelTest {
    @Mock
    private lateinit var context: Application

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var pokemonsObserver: Observer<List<PokemonResultModel>>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<Throwable>

    @Mock
    private lateinit var emptyObserver: Observer<Boolean>

    private val emptyRepository = FakePokedexEmptyRepository()
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
    fun `fetch pokemons SUCCESS`(){
        runTest {
            setupViewModel(successRepository)
            viewModel.fetchAllPokemon()
            Assert.assertTrue(viewModel.pokemons.value.isNullOrEmpty().not())
            Assert.assertNull(viewModel.error.value)
            Assert.assertFalse(viewModel.progress.value?:false)
        }
    }

    @Test
    fun `fetch pokemons ERROR`(){
        runTest {
            setupViewModel(errorRepository)
            viewModel.fetchAllPokemon()
            Assert.assertNull(viewModel.pokemons.value)
            Assert.assertNotNull(viewModel.error.value)
            Assert.assertFalse(viewModel.progress.value?:false)
        }
    }

    @Test
    fun `search pokemons SUCCESS`(){
        runTest {
            setupViewModel(successRepository)
            viewModel.searchByName("bulbasaur")
            Assert.assertTrue(viewModel.pokemons.value.isNullOrEmpty().not())
            Assert.assertNull(viewModel.error.value)
            Assert.assertFalse(viewModel.progress.value?:false)
        }
    }

    @Test
    fun `search pokemons NO RESULTS`(){
        runTest {
            setupViewModel(emptyRepository)
            viewModel.searchByName("bulbasaur")
            Assert.assertTrue(viewModel.pokemons.value.isNullOrEmpty())
            Assert.assertTrue(viewModel.empty.value?:false)
            Assert.assertNull(viewModel.error.value)
            Assert.assertFalse(viewModel.progress.value?:false)
        }
    }

    @Test
    fun `search pokemons ERROR`(){
        runTest {
            setupViewModel(errorRepository)
            viewModel.searchByName("bulbasaur")
            Assert.assertNull(viewModel.pokemons.value)
            Assert.assertNotNull(viewModel.error.value)
            Assert.assertFalse(viewModel.progress.value?:false)
        }
    }

    private fun setupViewModel(repository: PokedexRepository){
        viewModel = MainViewModel(
            FetchAllPokemonInteractor(repository),
            SearchPokemonByNameInteractor(repository),
            DownloadThumbnailsInteractor(repository)
        )
        viewModel.pokemons.observeForever(pokemonsObserver)
        viewModel.progress.observeForever(loadingObserver)
        viewModel.empty.observeForever(emptyObserver)
        viewModel.error.observeForever(errorObserver)
    }
}