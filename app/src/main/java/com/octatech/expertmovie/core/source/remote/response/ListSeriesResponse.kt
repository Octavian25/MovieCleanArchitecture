package com.octatech.expertmovie.core.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListSeriesResponse (
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<SeriesResponse>
    )