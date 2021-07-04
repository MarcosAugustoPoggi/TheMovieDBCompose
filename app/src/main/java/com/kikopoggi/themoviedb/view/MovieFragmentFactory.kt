package com.kikopoggi.themoviedb.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.kikopoggi.themoviedb.adapter.MovieRecyclerAdapter
import javax.inject.Inject

class MovieFragmentFactory @Inject constructor(
    private val movieRecyclerAdapter: MovieRecyclerAdapter,
    private val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className) {
            MovieListFragment::class.java.name -> MovieListFragment(movieRecyclerAdapter)
            MovieDetailFragment::class.java.name -> MovieDetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}