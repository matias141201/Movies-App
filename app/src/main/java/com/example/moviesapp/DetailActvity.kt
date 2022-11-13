package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.ActivityDetailActvityBinding
import com.example.moviesapp.model.Movie

class DetailActvity : AppCompatActivity() {


    companion object {
        const val EXTRA_MOVIE = "DetailActivity:MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (movie != null) {
            binding.tvMovieTitle.text = movie.title
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w780/${movie.backdrop_path}")
                .into(binding.ivBackDrop)
            binding.tvOverview.text = movie.overview
            bindingMovieInfo(binding.tvMovieInfo, movie)
        }

    }

    private fun bindingMovieInfo(tvMovieInfo: TextView, movie: Movie) {
        tvMovieInfo.text = buildSpannedString {
            bold { append("release date: ") }
            appendLine(movie.release_date)

            bold { append("popularitye: ") }
            appendLine(movie.popularity.toString())

            bold { append("original language: ") }
            appendLine(movie.original_language)

            bold { append("vote average: ") }
            appendLine(movie.vote_average.toString())

        }

    }
}