package com.example.moviesapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.databinding.ItemViewMovieBinding
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.MovieDBClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private val DarkMode = AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DarkMode
        screenSplash.setKeepOnScreenCondition { false }
        val moviesAdapter = MoviesAdapter(
            emptyList()
        ) { movie ->
            goTo(movie)
        }

        binding.recyclerview.adapter = moviesAdapter

        lifecycleScope.launch {
            val apikey = getString(R.string.api_key)
            val popularMovies = MovieDBClient.service.listPopularMovies(apikey)
            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()
            binding.viewLoading.isVisible = false


        }
    }

    private fun goTo(movie: Movie) {

        val intent = Intent(this, DetailActvity::class.java)
        intent.putExtra(DetailActvity.EXTRA_MOVIE, movie)

        startActivity(intent)


    }


}
