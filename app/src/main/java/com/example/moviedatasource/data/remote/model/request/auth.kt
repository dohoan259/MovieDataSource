package com.example.moviedatasource.data.remote.model.request

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateSessionRequest(
    @field:Json(name="request_token") val requestToken: String): Parcelable

@Parcelize
data class DeleteSessionRequest(
    @field:Json(name="session_id") val sessionId: String): Parcelable