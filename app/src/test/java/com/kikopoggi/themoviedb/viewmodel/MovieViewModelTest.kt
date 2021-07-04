package com.kikopoggi.themoviedb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kikopoggi.themoviedb.MainCoroutineRule
import com.kikopoggi.themoviedb.repository.FakeMovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel : MovieViewModel


    @Before
    fun setup() {
        // Test Double
        viewModel = MovieViewModel(FakeMovieRepository())
    }

    @Test
    fun `check if is page 1 from api`(){
        viewModel.getMovies()
        val value = viewModel.moviesList
        assertThat(value.value?.data?.page).isEqualTo(1)
    }

    @Test
    fun `Test receive correct title name`(){
        viewModel.getMovies()
        val value = viewModel.moviesList
        assertThat(value.value?.data?.results?.get(0)?.title).isEqualTo("Filme")
    }

    @Test
    fun `Test receive correct release_date`(){
        viewModel.getMovies()
        val value = viewModel.moviesList
        assertThat(value.value?.data?.results?.get(0)?.release_date).isEqualTo("2021-06-17")
    }

    @Test
    fun `Test receive correct vote_average`(){
        viewModel.getMovies()
        val value = viewModel.moviesList
        assertThat(value.value?.data?.results?.get(0)?.vote_average).isEqualTo(8.2)
    }

    @Test
    fun `Test receive correct poster_path`(){
        viewModel.getMovies()
        val value = viewModel.moviesList
        assertThat(value.value?.data?.results?.get(0)?.poster_path).isEqualTo("/1lk7QISmTam2iOzzUAQYs6voeFg.jpg")
    }

    @Test
    fun `Test receive correct original_language`(){
        viewModel.getMovies()
        val value = viewModel.moviesList
        assertThat(value.value?.data?.results?.get(0)?.original_language).isEqualTo("en")
    }
}