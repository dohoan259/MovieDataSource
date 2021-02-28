package com.example.moviedatasource.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.local.entity.MovieCollection

/**
 * A data class to represent the relationship set Movie-MovieCollection.
 * We create a separate table for this relation because it is a MxN relation.
 * The '@Relation' annotation from is suitable only for 1xN relations.
 */
@Entity(
    tableName = "movie_collection_ref",
    primaryKeys = ["movie_id", "name"],
    indices = [Index(value = ["movie_id", "name"])],
    foreignKeys = [
        ForeignKey(entity = Movie::class, parentColumns = ["movie_id"], childColumns = ["movie_id"]),
        ForeignKey(entity = MovieCollection::class, parentColumns = ["name"], childColumns = ["name"])
    ]
)
data class MovieCollectionCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "name")
    val collectionId: String
)