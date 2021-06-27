package com.octatech.expertmovie.core.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movieentities")
data class MovieEntity(
	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "movieId")
	val id: Int,

	@ColumnInfo(name = "overview")
	val overview: String,

	@ColumnInfo(name = "originalLanguage")
	val originalLanguage: String,

	@ColumnInfo(name = "originalTitle")
	val originalTitle: String,

	@ColumnInfo(name = "video")
	val video: Boolean,

	@ColumnInfo(name = "title")
	val title: String,

	@ColumnInfo(name = "posterPath")
	val posterPath: String,

	@ColumnInfo(name = "backdropPath")
	val backdropPath: String,

	@ColumnInfo(name = "releaseDate")
	val releaseDate: String,

	@ColumnInfo(name = "popularity")
	val popularity: Double,

	@ColumnInfo(name = "voteAverage")
	val voteAverage: Double,

	@ColumnInfo(name = "adult")
	val adult: Boolean,

	@ColumnInfo(name = "voteCount")
	val voteCount: Int,

	@ColumnInfo(name = "isFavorite")
	var isFavorite: Boolean
) : Parcelable
