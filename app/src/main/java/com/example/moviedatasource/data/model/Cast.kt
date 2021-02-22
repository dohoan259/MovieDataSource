package com.example.moviedatasource.data.model

import androidx.room.*

/**
 * A data class to represent the Movie-Actor relationship set.
 * We create a table for this relation instead because it has MxN
 * mapping cardinality. The `@Relation` annotation from room is suitable
 * for 1XN relations only.
 */
@Entity(
    tableName = "casts",
    foreignKeys = [ForeignKey(
        entity = Movie::class,
        parentColumns = ["id"],
        childColumns = ["movie_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Cast(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "cast_members")
    val castMembersIds: List<Int>
) {
    @Ignore
    var castMembers: List<Actor>? = null
}