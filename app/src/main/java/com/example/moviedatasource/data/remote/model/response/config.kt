package com.example.moviedatasource.data.remote.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImagesConfiguration(
    @Json(name="base_url") val baseUrl: String,
    @Json(name="secure_base_url") val secureBaseUrl: String,
    @Json(name="backdrop_sizes") val backdropSizes: List<String>,
    @Json(name="logo_sizes") val logoSizes: List<String>,
    @Json(name="poster_sizes") val posterSizes: List<String>,
    @Json(name="profile_sizes") val profileSizes: List<String>): Parcelable

@Parcelize
data class ConfigurationResponse(
    val images: ImagesConfiguration
): Parcelable