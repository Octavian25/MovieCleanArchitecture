package com.octatech.expertmovie.core.di

import android.content.Context
import com.octatech.expertmovie.core.domain.repository.IMovieRepository
import com.octatech.expertmovie.core.domain.usecase.MovieInteractor
import com.octatech.expertmovie.core.domain.usecase.MovieUseCase
import com.octatech.expertmovie.core.source.MovieRepository
import com.octatech.expertmovie.core.source.local.LocalDataSource
import com.octatech.expertmovie.core.source.local.room.MovieDatabase
import com.octatech.expertmovie.core.source.remote.RemoteDataSource
import com.octatech.expertmovie.core.source.remote.network.ApiConfig
import com.octatech.expertmovie.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}