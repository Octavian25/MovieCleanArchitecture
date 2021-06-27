package com.octatech.expertmovie.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.octatech.expertmovie.BuildConfig
import com.octatech.expertmovie.R
import com.octatech.expertmovie.core.domain.model.Movie
import com.octatech.expertmovie.core.ui.ViewModelFactory
import com.octatech.expertmovie.databinding.ActivityDetailBinding
import com.octatech.expertmovie.ui.home.HomeActivity

class DetailActivityMovie : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    private lateinit var detailViewModel : DetailMoviesViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailMoviesViewModel::class.java]

        val movies = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(movies)

        binding.ivBack.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showDetailMovie(movies: Movie?) {
        movies?.let {
            Glide.with(this)
                .load(BuildConfig.baseImage +movies.posterPath)
                .into(binding.ivDetail)
            binding.tvTitleDetail.text = movies.title
            binding.tvDescDetail.text = movies.overview
            binding.tvRatingDetail.text = "⭐ " + movies.voteAverage.toString()

            var statusFavorite = movies.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnBookmarkMovie.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(movies, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnBookmarkMovie.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite))
        } else {
            binding.btnBookmarkMovie.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.unfavorite))
        }
    }
}