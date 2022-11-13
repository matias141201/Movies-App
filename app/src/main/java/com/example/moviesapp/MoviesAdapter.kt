package com.example.moviesapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.model.Movie
import com.example.moviesapp.databinding.ItemViewMovieBinding


class MoviesAdapter(
    var movies: List<Movie>,
    private val movieClickedListener: (Movie) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { movieClickedListener(movie) }
    }

    override fun getItemCount() = movies.size


    class ViewHolder(private val binding: ItemViewMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            VoteAverageColor(movie)
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w185/${movie.poster_path}")
                .into(binding.ivCover)
        }

        fun VoteAverageColor(movie: Movie) {
            binding.tvTitle.text = movie.title
            if (movie.vote_average <= 3.9) {
                binding.tvVoteAverage.setTextColor(Color.RED)
            } else if (movie.vote_average <= 5.9) {
                binding.tvVoteAverage.setTextColor(Color.YELLOW)
            } else {
                binding.tvVoteAverage.setTextColor(Color.GREEN)
            }
            binding.tvVoteAverage.text = movie.vote_average.toString()

        }

    }
}