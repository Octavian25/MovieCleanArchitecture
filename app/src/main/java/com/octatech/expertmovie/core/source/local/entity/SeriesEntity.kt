package com.octatech.expertmovie.core.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "seriesEntities")
data class SeriesEntity(
	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "seriesId")
	val id: Int,

	@ColumnInfo(name = "backdrop_path")
	val backdropPath: String,

	@ColumnInfo(name = "first_air_date")
	val firstAirDate: String,

	@ColumnInfo(name = "name")
	val name: String,

	@ColumnInfo(name = "original_language")
	val originalLanguage: String,

	@ColumnInfo(name = "original_name")
	val originalName: String,

	@ColumnInfo(name = "overview")
	val overview: String,

	@ColumnInfo(name = "popularity")
	val popularity: Double,

	@ColumnInfo(name = "poster_path")
	val posterPath: String,

	@ColumnInfo(name = "vote_average")
	val voteAverage: Double,

	@ColumnInfo(name = "vote_count")
	val voteCount: Int,

	@ColumnInfo(name = "isFavorite")
	var isFavorite: Boolean
) : Parcelable
