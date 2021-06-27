package com.octatech.expertmovie.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
	val backdropPath: String,
	val firstAirDate: String,
	val overview: String,
	val originalLanguage: String,
	val originalName: String,
	val popularity: Double,
	val voteAverage: Double,
	val name: String,
	val id: Int,
	val voteCount: Int,
	val posterPath: String,
	val isFavorite: Boolean,
) : Parcelable

