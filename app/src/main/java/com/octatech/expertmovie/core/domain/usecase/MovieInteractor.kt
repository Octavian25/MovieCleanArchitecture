package com.octatech.expertmovie.core.domain.usecase

import com.octatech.expertmovie.core.domain.model.Movie
import com.octatech.expertmovie.core.domain.model.Series
import com.octatech.expertmovie.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

    override fun getAllSeries() = movieRepository.getAllSeries()

    override fun getFavoriteSeries() = movieRepository.getFavoriteSeries()

    override fun setFavoriteSeries(series: Series, state: Boolean) = movieRepository.setFavoriteSeries(series, state)
}