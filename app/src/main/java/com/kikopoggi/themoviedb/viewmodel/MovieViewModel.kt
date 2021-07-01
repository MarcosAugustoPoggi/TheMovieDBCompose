package com.kikopoggi.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kikopoggi.themoviedb.model.moviesResult
import com.kikopoggi.themoviedb.repository.MovieRepository
import com.kikopoggi.themoviedb.repository.MovieRepositoryInterface
import com.kikopoggi.themoviedb.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {


    private val movies = MutableLiveData<Resource<moviesResult>>()
    val moviesList: LiveData<Resource<moviesResult>>
        get() = movies

    fun getMovies() {

        movies.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getMovies()
            movies.value = response
        }


    }
}