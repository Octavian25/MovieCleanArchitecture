package com.octatech.expertmovie.core.domain.repository

import androidx.lifecycle.LiveData
import com.octatech.expertmovie.core.domain.model.Movie
import com.octatech.expertmovie.core.domain.model.Series
import com.octatech.expertmovie.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getAllSeries(): Flow<Resource<List<Series>>>

    fun getFavoriteMovie(): Flow<List<Movie>>
    fun getFavoriteSeries(): Flow<List<Series>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun setFavoriteSeries(series: Series, state: Boolean)
}