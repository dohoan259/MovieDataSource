package com.example.moviedatasource.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    val genreId: Int,
    @ColumnInfo(name = "name")
    val name: String
)