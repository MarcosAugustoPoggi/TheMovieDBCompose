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
                    return@let Resource.Success(it)
                } ?: Resource.Error("Error")
            } else {
                Resource.Error("Error")
            }

        } catch (e: Exception) {
            Resource.Error("Error or no  data!")
        }
    }

}