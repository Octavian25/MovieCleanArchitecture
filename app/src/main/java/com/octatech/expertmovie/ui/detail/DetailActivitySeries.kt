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
import com.octatech.expertmovie.core.domain.model.Series
import com.octatech.expertmovie.core.ui.ViewModelFactory
import com.octatech.expertmovie.databinding.ActivityDetailBinding
import com.octatech.expertmovie.ui.home.HomeActivity

class DetailActivitySeries : AppCompatActivity() {
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

        val series = intent.getParcelableExtra<Series>(EXTRA_DATA)
        showDetailMovie(series)

        binding.ivBack.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showDetailMovie(series: Series?) {
        series?.let {
            Glide.with(this)
                .load( BuildConfig.baseImage +series.posterPath)
                .into(binding.ivDetail)
            binding.tvTitleDetail.text = series.name
            binding.tvDescDetail.text = series.overview
            binding.tvRatingDetail.text = "⭐ " + series.voteAverage.toString()

            var statusFavorite = series.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnBookmarkMovie.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteSeries(series, statusFavorite)
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