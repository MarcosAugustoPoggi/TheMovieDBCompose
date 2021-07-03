package com.kikopoggi.themoviedb.repository

import com.kikopoggi.themoviedb.model.moviesResult
import com.kikopoggi.themoviedb.util.Resource

interface MovieRepositoryInterface {

    suspend fun getMovies(page : Int) : Resource<moviesResult>

}