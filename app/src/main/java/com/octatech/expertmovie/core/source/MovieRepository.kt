package com.octatech.expertmovie.core.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.octatech.expertmovie.core.domain.model.Movie
import com.octatech.expertmovie.core.domain.model.Series
import com.octatech.expertmovie.core.domain.repository.IMovieRepository
import com.octatech.expertmovie.core.source.local.LocalDataSource
import com.octatech.expertmovie.core.source.remote.RemoteDataSource
import com.octatech.expertmovie.core.source.remote.network.ApiResponse
import com.octatech.expertmovie.core.source.remote.response.MovieResponse
import com.octatech.expertmovie.core.source.remote.response.SeriesResponse
import com.octatech.expertmovie.core.utils.AppExecutors
import com.octatech.expertmovie.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomainMovie(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = DataMapper.mapResponsesToEntitiesMovie(data)
                localDataSource.insertMovie(tourismList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomainMovie(it) }
    }

    override fun setFavoriteMovie(tourism: Movie, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntityMovie(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(tourismEntity, state) }
    }

    override fun getAllSeries(): Flow<Resource<List<Series>>> =
        object : NetworkBoundResource<List<Series>, List<SeriesResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Series>> {
                return localDataSource.getALlSeries().map { DataMapper.mapEntitiesToDomainSeries(it) }
            }

            override fun shouldFetch(data: List<Series>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SeriesResponse>>> =
                remoteDataSource.getAllSeries()

            override suspend fun saveCallResult(data: List<SeriesResponse>) {
                val seriesList = DataMapper.mapResponsesToEntitiesSeries(data)
                localDataSource.insertSeries(seriesList)
            }
        }.asFlow()

    override fun getFavoriteSeries(): Flow<List<Series>> {
        return localDataSource.getFavoriteSeries().map { DataMapper.mapEntitiesToDomainSeries(it) }
    }

    override fun setFavoriteSeries(series: Series, state: Boolean) {
        val seriesEntity = DataMapper.mapDomainToEntitySeries(series)
        appExecutors.diskIO().execute { localDataSource.setFavoriteSeries(seriesEntity, state) }
    }
}