package com.kikopoggi.themoviedb.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kikopoggi.themoviedb.R
import com.kikopoggi.themoviedb.viewmodel.MovieViewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    lateinit var viewModel: MovieViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
    }
}