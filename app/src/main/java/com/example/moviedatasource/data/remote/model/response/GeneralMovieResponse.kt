package com.example.moviedatasource.data.remote.model.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class GeneralMovieResponse(
    @Json(name = "adult") val isAdultMovie: Boolean,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "overview") val overview: String,
    @Json(name = "release_date") var releaseDate: Date,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "id") val id: Int,
    @Json(name = "original_title") val originalTitle: String?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "title") val title: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "vote_count") val voteCount: Int?,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") var voteAverage: Double
) : Parcelable

@Parcelize
data class ErrorResponse(
    @Json(name = "status_code") val statusCode: String,
    @Json(name = "status_message") val statusMessage: String
) : Parcelable