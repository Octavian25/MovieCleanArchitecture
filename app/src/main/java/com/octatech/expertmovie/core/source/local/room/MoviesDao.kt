package com.octatech.expertmovie.core.source.local.room

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.room.*
import com.octatech.expertmovie.core.source.local.entity.MovieEntity
import com.octatech.expertmovie.core.source.local.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movieentities")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM seriesEntities")
    fun getAllSeries(): Flow<List<SeriesEntity>>

    @Query("SELECT * FROM movieentities where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM seriesEntities where isFavorite = 1")
    fun getFavoriteSeries(): Flow<List<SeriesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeries(series: List<SeriesEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Update
    fun updateFavoriteSeries(movie: SeriesEntity)
}