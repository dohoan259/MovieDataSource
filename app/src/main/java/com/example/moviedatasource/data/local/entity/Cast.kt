package com.example.moviedatasource.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * A data class to represent the Movie-Actor relationship set.
 * We create a table for this relation instead because it has MxN
 * mapping cardinality. The `@Relation` annotation from room is suitable
 * for 1XN relations only.
 */
@Entity(
    tableName = "casts",
    primaryKeys = ["movie_id", "actor_id"],
    indices = [Index(value = ["movie_id", "actor_id"])],
    foreignKeys = [
        ForeignKey(
            entity = Movie::class,
        parentColumns = ["movie_id"],
        childColumns = ["movie_id"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Actor::class,
            parentColumns = ["actor_id"],
            childColumns = ["actor_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class
Cast(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "actor_id")
    val actorId: Int
)