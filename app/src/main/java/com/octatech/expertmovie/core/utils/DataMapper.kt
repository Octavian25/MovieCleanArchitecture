package com.octatech.expertmovie.core.utils

import com.octatech.expertmovie.core.domain.model.Movie
import com.octatech.expertmovie.core.domain.model.Series
import com.octatech.expertmovie.core.source.local.entity.MovieEntity
import com.octatech.expertmovie.core.source.local.entity.SeriesEntity
import com.octatech.expertmovie.core.source.remote.response.MovieResponse
import com.octatech.expertmovie.core.source.remote.response.SeriesResponse

object DataMapper {
    fun mapResponsesToEntitiesMovie(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                overview =it.overview,
                originalLanguage =it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }
    fun mapEntitiesToDomainMovie(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview =it.overview,
                originalLanguage =it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntityMovie(input: Movie) = MovieEntity(
        id = input.id,
        overview =input.overview,
        originalLanguage =input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        title = input.title,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        adult = input.adult,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )

    fun mapResponsesToEntitiesSeries(input: List<SeriesResponse>): List<SeriesEntity> {
        val seriesList = ArrayList<SeriesEntity>()
        input.map {
            val series = SeriesEntity(
               id = it.id,
                backdropPath = it.backdropPath,
                firstAirDate = it.firstAirDate,
                name = it.name,
                originalLanguage = it.originalLanguage,
                originalName = it.originalName,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            seriesList.add(series)
        }
        return seriesList
    }
    fun mapEntitiesToDomainSeries(input: List<SeriesEntity>): List<Series> =
        input.map {
            Series(
                id = it.id,
                backdropPath = it.backdropPath,
                firstAirDate = it.firstAirDate,
                name = it.name,
                originalLanguage = it.originalLanguage,
                originalName = it.originalName,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntitySeries(input: Series) = SeriesEntity(
        id = input.id,
        backdropPath = input.backdropPath,
        firstAirDate = input.firstAirDate,
        name = input.name,
        originalLanguage = input.originalLanguage,
        originalName = input.originalName,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )
}