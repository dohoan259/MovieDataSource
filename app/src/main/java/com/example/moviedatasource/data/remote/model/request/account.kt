package com.example.moviedatasource.data.remote.model.request

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

internal enum class MediaTypes(val mediaName: String) {
    MOVIE("movie"), TV("tv")
}

@Parcelize
data class ToggleMediaFavouriteStatusRequest(
    @field:Json(name = "media_type") val mediaType: String,
    @field:Json(name = "media_id") val mediaId: Int,
    @field:Json(name = "favorite") val favorite: Boolean
) : Parcelable

@Parcelize
data class ToggleMediaWatchlistStatusRequest(
    @field:Json(name = "media_type") val mediaType: String,
    @field:Json(name = "media_id") val mediaId: Int,
    @field:Json(name = "watchlist") val watchlist: Boolean
) : Parcelable