package com.kikopoggi.themoviedb.repository

import com.kikopoggi.themoviedb.api.MoviesApi
import com.kikopoggi.themoviedb.model.moviesResult
import com.kikopoggi.themoviedb.util.Resource
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val moviesApi: MoviesApi
): MovieRepositoryInterface {

    override suspend fun getMovies(): Resource<moviesResult> {
        return try {

            val response = moviesApi.getMovies()

            if (response.isSuccessful) {
                response.body()?.let {
                        return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }

        } catch (e: Exception) {
            Resource.error("$e.message", null)
        }
    }

}