package com.kikopoggi.themoviedb.view

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.kikopoggi.themoviedb.R
import com.kikopoggi.themoviedb.databinding.FragmentMovieDetailBinding
import com.kikopoggi.themoviedb.util.Constants
import com.kikopoggi.themoviedb.viewmodel.MovieViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Inject

class MovieDetailFragment @Inject constructor(
    val glide : RequestManager
) : Fragment(R.layout.fragment_movie_detail) {

   lateinit var viewModel: MovieViewModel
   private var _binding : FragmentMovieDetailBinding? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        val binding = FragmentMovieDetailBinding.bind(view)
         _binding = binding

        val movie = viewModel.movie

        movie?. let { movie ->

            val moviePoster : Uri = Uri.parse(Constants.POSTER_BASE_URL + movie.poster_path)
            glide.load(moviePoster).into(binding.ivPosterMovie)

            binding.tvTitle.text = movie.title
            binding.tvVote.text = movie.vote_average.toString()

            if (!movie.release_date.isNullOrBlank()) {
                val date = LocalDate.parse(movie.release_date)
                val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                binding.tvData.text = (date.format(formatter)).toString()
            }
            
            binding.tvIdioma.text = movie.original_language
            binding.tvOverview.text = movie.overview

        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}