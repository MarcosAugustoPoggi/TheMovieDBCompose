package com.kikopoggi.themoviedb.view

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.kikopoggi.themoviedb.R
import com.kikopoggi.themoviedb.databinding.FragmentMovieDetailBinding
import com.kikopoggi.themoviedb.util.Constants
import com.kikopoggi.themoviedb.viewmodel.MovieViewModel
import javax.inject.Inject

class MovieDetailFragment @Inject constructor(
    val glide : RequestManager
) : Fragment(R.layout.fragment_movie_detail) {

   lateinit var viewModel: MovieViewModel
   private var _binding : FragmentMovieDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        val binding = FragmentMovieDetailBinding.bind(view)
         _binding = binding

        val moviePoster : Uri = Uri.parse(Constants.POSTER_BASE_URL + viewModel.movie?.poster_path)

        glide.load(moviePoster).into(binding.ivPosterMovie)
        binding.tvTitle.text = viewModel.movie?.title
        binding.tvVote.text = viewModel.movie?.vote_average.toString()
        binding.tvData.text = viewModel.movie?.release_date
        binding.tvIdioma.text = viewModel.movie?.original_language

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}