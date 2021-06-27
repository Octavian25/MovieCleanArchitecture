package com.octatech.expertmovie.core.domain.usecase

import androidx.lifecycle.LiveData
import com.octatech.expertmovie.core.domain.model.Movie
import com.octatech.expertmovie.core.domain.model.Series
import com.octatech.expertmovie.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(tourism: Movie, state: Boolean)
    fun getAllSeries(): Flow<Resource<List<Series>>>
    fun getFavoriteSeries(): Flow<List<Series>>
    fun setFavoriteSeries(tourism: Series, state: Boolean)
}