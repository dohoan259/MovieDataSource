package com.example.moviedatasource.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "actors")
data class Actor(
    @PrimaryKey
    @ColumnInfo(name = "actor_id")
    val actorId: Int,
    @ColumnInfo(name = "profile_picture_url")
    val profilePictureUrl: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "birthday")
    val birthday: Date?,
    @ColumnInfo(name = "biography")
    val biography: String?,
    @ColumnInfo(name = "is_model_complete")
    val isModelComplete: Boolean
)