package com.kikopoggi.themoviedb.api

import com.kikopoggi.themoviedb.model.moviesResult
import com.kikopoggi.themoviedb.util.Constants.API_KEY
import com.kikopoggi.themoviedb.util.Constants.LANGUAGE
import com.kikopoggi.themoviedb.util.Constants.PAGE_INIT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MoviesApi {

    // top_rated popular now_playing upcoming -> All working

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("page") page: Int = PAGE_INIT,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language : String = LANGUAGE
    ): Response<moviesResult>
}