package com.kikopoggi.themoviedb.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kikopoggi.themoviedb.R
import com.kikopoggi.themoviedb.model.Result
import com.kikopoggi.themoviedb.util.Constants.POSTER_BASE_URL
import javax.inject.Inject

class MovieRecyclerAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var movies: List<Result>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieRecyclerAdapter.MovieViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieRecyclerAdapter.MovieViewHolder, position: Int) {
        val movieView = holder.itemView.findViewById<ImageView>(R.id.ivPosterPath)
        val titleText = holder.itemView.findViewById<TextView>(R.id.tvTitle)
        val dateText = holder.itemView.findViewById<TextView>(R.id.tvData)
        val voteText = holder.itemView.findViewById<TextView>(R.id.tvVote)
        val movie = movies[position]
        holder.itemView.apply {
            val moviePoster : Uri = Uri.parse(POSTER_BASE_URL + movie.poster_path)
            glide.load(moviePoster).into(movieView)
            titleText.text = movie.title
            dateText.text = movie.release_date
            voteText.text = movie.vote_average.toString()
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}