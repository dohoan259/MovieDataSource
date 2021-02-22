package com.example.moviedatasource.data.remote.model.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gravatar(
    @Json(name="hash") val hash: String): Parcelable

@Parcelize
data class Avatar(
    @Json(name="gravatar") val gravatar: Gravatar): Parcelable

@Parcelize
data class AccountDetailsResponse(
    @Json(name="avatar") val avatar: Avatar,
    @Json(name="id") val id: Int,
    @Json(name="name") val name: String,
    @Json(name="include_adult") val includeAdult: Boolean,
    @Json(name="username") val username: String): Parcelable

@Parcelize
data class MovieWatchlistResponse(
    @Json(name="page") val page: Int,
    @Json(name="results") val results: List<GeneralMovieResponse>,
    @Json(name="total_pages") val totalPages: Int,
    @Json(name="total_results") val totalResults: Int): Parcelable

@Parcelize
data class FavouriteMoviesResponse(
    @Json(name="page") val page: Int,
    @Json(name="results") val results: List<GeneralMovieResponse>,
    @Json(name="total_pages") val totalPages: Int,
    @Json(name="total_results") val totalResults: Int): Parcelable

@Parcelize
data class ToggleFavouriteResponse(
    @Json(name="status_code") val statusCode: Int,
    @Json(name="status_message") val statusMessage: String): Parcelable

@Parcelize
data class ToggleWatchlistResponse(
    @Json(name="status_code") val statusCode: Int,
    @Json(name="status_message") val statusMessage: String): Parcelable
