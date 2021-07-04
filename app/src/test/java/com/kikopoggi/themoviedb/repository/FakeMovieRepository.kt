package com.kikopoggi.themoviedb.repository


import com.kikopoggi.themoviedb.model.Result
import com.kikopoggi.themoviedb.model.moviesResult
import com.kikopoggi.themoviedb.util.Resource


class FakeMovieRepository : MovieRepositoryInterface {

    private var film1 = Result(false,"/620hnMVLu6RSZW6a5rwO8gqpt0t.jpg",
        listOf(),0,"en","Filme","Overview",
    100.0,"/1lk7QISmTam2iOzzUAQYs6voeFg.jpg","2021-06-17",
        "Filme", false, 8.2,1000)

    private var movies = mutableListOf(film1)

    override suspend fun getMovies(): Resource<moviesResult> {
        return Resource.success(moviesResult(1, movies,1,1))
    }


}


