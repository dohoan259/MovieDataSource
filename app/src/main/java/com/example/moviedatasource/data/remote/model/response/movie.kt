package com.example.moviedatasource.data.remote.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class GenrePair(
    @Json(name="id") val id: Int,
    @Json(name="name") val name: String): Parcelable

@Parcelize
data class MovieResponse (
    @Json(name="adult") val isAdult: Boolean,
    @Json(name="backdrop_path") var backdropPath: String?,
    @Json(name="budget") val budget: Long,
    @Json(name="genres") val genres: List<GenrePair>,
    @Json(name="homepage") val homepage: String?,
    @Json(name="id") val id: Int,
    @Json(name="imdb_id") val imdbId: String?,
    @Json(name="original_language") val originalLanguage: String,
    @Json(name="original_title") val originalTitle: String,
    @Json(name="overview") val overview: String?,
    @Json(name="popularity") val popularity: Double,
    @Json(name="poster_path") var posterPath: String?,
    @Json(name="release_date") var releaseDate: Date,
    @Json(name="revenue") val revenue: Long,
    @Json(name="runtime") val runtime: Int?,
    @Json(name="title") val title: String,
    @Json(name="vote_average") var voteAverage: Double,
    @Json(name="vote_count") val voteCount: Int): Parcelable

@Parcelize
data class MovieStatesResponse(
    @Json(name="favorite") val isFavourited: Boolean?,
    @Json(name="watchlist") val isWatchlisted: Boolean?): Parcelable

@Parcelize
data class MovieVideo(
    @Json(name="id") val id: String,
    @Json(name="key") val key: String,
    @Json(name="name") val name: String,
    @Json(name="site") val site: String,
    @Json(name="size") val size: Int,
    @Json(name="type") val type: String): Parcelable

@Parcelize
data class MovieVideosResponse(
    @Json(name="id") val id: Int,
    @Json(name="results") val results: List<MovieVideo>): Parcelable

@Parcelize
data class CastMember(
    @Json(name="cast_id") val castId: Int,
    @Json(name="character") val character: String,
    @Json(name="credit_id") val creditId: String,
    @Json(name="gender") val gender: Int?,
    @Json(name="id") val id: Int,
    @Json(name="name") val name: String,
    @Json(name="order") val order: Int,
    @Json(name="profile_path") val profilePath: String?): Parcelable

@Parcelize
data class MovieCreditsResponse(
    @Json(name="id") val id: Int,
    @Json(name="cast") val cast: List<CastMember>): Parcelable

@Parcelize
data class SimilarMoviesResponse(
    @Json(name="page") val page: Int,
    @Json(name="results") val results: List<GeneralMovieResponse>,
    @Json(name="total_results") val totalResults: Int,
    @Json(name="total_pages") val totalPages: Int): Parcelable