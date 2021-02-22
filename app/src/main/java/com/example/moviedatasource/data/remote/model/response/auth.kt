package com.example.moviedatasource.data.remote.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class GuestSessionResponse(
    @Json(name="success") val success: Boolean,
    @Json(name="guest_session_id") val id: String,
    @Json(name="expires_at") val expiresAt: String): Parcelable


@Parcelize
data class RequestTokenResponse(
    @Json(name="success") val success: Boolean,
    @Json(name="expires_at") val expiresAt: String,
    @Json(name="request_token") val requestToken: String): Parcelable

@Parcelize
data class CreateSessionResponse(
    @Json(name="success") val success: Boolean,
    @Json(name="session_id") val sessionId: String): Parcelable