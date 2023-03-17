package com.challenge.pokedex

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.pokedex.domain.interactor.DownloadThumbnailsInteractor
import com.challenge.pokedex.domain.interactor.FetchAllPokemonInteractor
import com.challenge.pokedex.domain.interactor.GetPokemonEvolutionsInteractor
import com.challenge.pokedex.domain.interactor.SearchPokemonByNameInteractor
import com.challenge.pokedex.domain.repository.PokedexRepository
import com.challenge.pokedex.fake.FakePokedexEmptyRepository
import com.challenge.pokedex.fake.FakePokedexErrorRepository
import com.challenge.pokedex.fake.FakePokedexSuccessRepository
import com.challenge.pokedex.ui.model.EvolutionModel
import com.challenge.pokedex.ui.viewmodel.EvolutionsViewModel
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
class PokemonEvolutionsViewModelTest {

    private lateinit var viewModel: EvolutionsViewModel

    @Mock
    private lateinit var evolutionsObserver: Observer<List<EvolutionModel>>

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
    fun `get evolutions SUCCESS`(){
        runTest {
            setupViewModel(successRepository)
            viewModel.getEvolutions("1")
            Assert.assertTrue(viewModel.evolutions.value.isNullOrEmpty().not())
            Assert.assertNull(viewModel.error.value)
            Assert.assertFalse(viewModel.progress.value?:false)
        }
    }

    @Test
    fun `search evolutions NO EVOLUTIONS`(){
        runTest {
            setupViewModel(emptyRepository)
            viewModel.getEvolutions("151")
            Assert.assertTrue(viewModel.evolutions.value.isNullOrEmpty())
            Assert.assertTrue(viewModel.empty.value?:false)
            Assert.assertNull(viewModel.error.value)
            Assert.assertFalse(viewModel.progress.value?:false)
        }
    }

    private fun setupViewModel(repository: PokedexRepository){
        viewModel = EvolutionsViewModel(
            GetPokemonEvolutionsInteractor(repository)
        )
        viewModel.evolutions.observeForever(evolutionsObserver)
        viewModel.progress.observeForever(loadingObserver)
        viewModel.empty.observeForever(emptyObserver)
        viewModel.error.observeForever(errorObserver)
    }
}