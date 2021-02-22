package com.example.moviedatasource.data.remote.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiscoverMoviesResponse(
    @Json(name = "page") val page: Int?,
    @Json(name = "results") val results: List<GeneralMovieResponse>,
    @Json(name = "total_results") val totalResults: Int?,
    @Json(name = "total_pages") val totalPages: Int?
) : Parcelable