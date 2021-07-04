package com.kikopoggi.themoviedb.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikopoggi.themoviedb.R
import com.kikopoggi.themoviedb.adapter.MovieRecyclerAdapter
import com.kikopoggi.themoviedb.databinding.FragmentMovieListBinding
import com.kikopoggi.themoviedb.util.Status
import com.kikopoggi.themoviedb.viewmodel.MovieViewModel
import javax.inject.Inject

class MovieListFragment @Inject constructor(
    private val movieRecyclerAdapter: MovieRecyclerAdapter
): Fragment(R.layout.fragment_movie_list) {

    lateinit var viewModel: MovieViewModel

    private var _binding : FragmentMovieListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        val binding = FragmentMovieListBinding.bind(view)
        _binding = binding
        viewModel.getMovies()

        subscribeToObservers()

        binding.recyclerView.adapter = movieRecyclerAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        movieRecyclerAdapter.setOnMovieItemClickListener { movieClick ->
            viewModel.movie = movieClick
            findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)

        }
    }

    private fun subscribeToObservers() {
        viewModel.moviesList.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                   val movies = it.data?.results
                    movieRecyclerAdapter.movies = movies!!
                    _binding?.progressBar?.visibility = View.GONE
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_SHORT).show()
                    _binding?.progressBar?.visibility = View.GONE
                }
                Status.LOADING -> {
                    _binding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}