package com.example.moviedatasource.data.remote.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
internal data class PersonResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "profile_path") val profilePath: String?,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "birthday") val birthday: Date?,
    @field:Json(name = "biography") val biography: String
) : Parcelable