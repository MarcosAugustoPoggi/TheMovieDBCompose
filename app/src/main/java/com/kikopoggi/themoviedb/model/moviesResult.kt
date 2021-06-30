package com.kikopoggi.themoviedb.model

data class moviesResult(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)