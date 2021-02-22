package com.example.moviedatasource.data.remote.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class GuestSessionResponse(
    @field:Json(name="success") val success: Boolean,
    @field:Json(name="guest_session_id") val id: String,
    @field:Json(name="expires_at") val expiresAt: String): Parcelable


@Parcelize
data class RequestTokenResponse(
    @field:Json(name="success") val success: Boolean,
    @field:Json(name="expires_at") val expiresAt: String,
    @field:Json(name="request_token") val requestToken: String): Parcelable

@Parcelize
data class CreateSessionResponse(
    @field:Json(name="success") val success: Boolean,
    @field:Json(name="session_id") val sessionId: String): Parcelable