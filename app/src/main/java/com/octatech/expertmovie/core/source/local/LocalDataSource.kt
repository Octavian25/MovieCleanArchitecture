package com.octatech.expertmovie.core.source.local

import androidx.lifecycle.LiveData
import com.octatech.expertmovie.core.source.local.entity.MovieEntity
import com.octatech.expertmovie.core.source.local.entity.SeriesEntity
import com.octatech.expertmovie.core.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MoviesDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MoviesDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovies()
    fun getALlSeries(): Flow<List<SeriesEntity>> = movieDao.getAllSeries()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()
    fun getFavoriteSeries(): Flow<List<SeriesEntity>> = movieDao.getFavoriteSeries()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)
    suspend fun insertSeries(seriesList: List<SeriesEntity>) = movieDao.insertSeries(seriesList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
    fun setFavoriteSeries(series: SeriesEntity, newState: Boolean) {
        series.isFavorite = newState
        movieDao.updateFavoriteSeries(series)
    }
}