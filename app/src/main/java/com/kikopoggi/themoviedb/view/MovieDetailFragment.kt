package com.kikopoggi.themoviedb.view

import androidx.fragment.app.Fragment
import com.bumptech.glide.RequestManager
import com.kikopoggi.themoviedb.R
import javax.inject.Inject

class MovieDetailFragment @Inject constructor(
    val glide : RequestManager
) : Fragment(R.layout.fragment_movie_detail) {
}