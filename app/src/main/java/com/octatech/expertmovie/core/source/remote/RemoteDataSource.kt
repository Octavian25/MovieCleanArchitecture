package com.octatech.expertmovie.core.source.remote

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.octatech.expertmovie.BuildConfig
import com.octatech.expertmovie.core.source.remote.network.ApiResponse
import com.octatech.expertmovie.core.source.remote.network.ApiService
import com.octatech.expertmovie.core.source.remote.response.ListMovieResponse
import com.octatech.expertmovie.core.source.remote.response.ListSeriesResponse
import com.octatech.expertmovie.core.source.remote.response.MovieResponse
import com.octatech.expertmovie.core.source.remote.response.SeriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    @SuppressLint("CheckResult")
    fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovie(BuildConfig.API_KEY, BuildConfig.language)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    @SuppressLint("CheckResult")
    fun getAllSeries(): Flow<ApiResponse<List<SeriesResponse>>> {
        return flow {
            try {
                val response = apiService.getSeries(BuildConfig.API_KEY, BuildConfig.language, 1)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}