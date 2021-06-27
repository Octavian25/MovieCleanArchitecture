package com.octatech.expertmovie.core.source.remote.network

import com.octatech.expertmovie.core.source.remote.response.ListMovieResponse
import com.octatech.expertmovie.core.source.remote.response.ListSeriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
     suspend fun getMovie(@Query("api_key") api_key : String, @Query("language") language : String): ListMovieResponse

    @GET("tv/on_the_air")
     suspend fun getSeries(@Query("api_key") api_key : String, @Query("language") language : String,@Query("page") page : Int,): ListSeriesResponse
}