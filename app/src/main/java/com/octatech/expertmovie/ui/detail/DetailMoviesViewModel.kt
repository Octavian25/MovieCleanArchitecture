package com.octatech.expertmovie.ui.detail

import androidx.lifecycle.ViewModel
import com.octatech.expertmovie.core.domain.model.Movie
import com.octatech.expertmovie.core.domain.model.Series
import com.octatech.expertmovie.core.domain.usecase.MovieUseCase

class DetailMoviesViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
        fun setFavoriteMovie(movie: Movie, newStatus : Boolean){
                    movieUseCase.setFavoriteMovie(movie, newStatus)
        }

    fun setFavoriteSeries(series: Series, newStatus : Boolean){
        movieUseCase.setFavoriteSeries(series, newStatus)
    }
}