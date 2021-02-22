package com.example.moviedatasource.data.remote.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
internal data class PersonResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "profile_path") val profilePath: String?,
    @Json(name = "name") val name: String,
    @Json(name = "birthday") val birthday: Date?,
    @Json(name = "biography") val biography: String
) : Parcelable