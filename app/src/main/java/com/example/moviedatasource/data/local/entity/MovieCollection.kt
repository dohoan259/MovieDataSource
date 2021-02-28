package com.example.moviedatasource.data.local.entity

import androidx.room.*
import com.example.moviedatasource.data.local.entity.CollectionNames.FAVOURITES_NAME
import com.example.moviedatasource.data.local.entity.CollectionNames.IN_THEATRES_NAME
import com.example.moviedatasource.data.local.entity.CollectionNames.POPULAR_NAME
import com.example.moviedatasource.data.local.entity.CollectionNames.TOP_RATED_NAME
import com.example.moviedatasource.data.local.entity.CollectionNames.WATCHLIST_NAME

@Entity(tableName = "collections")
data class MovieCollection(
    @PrimaryKey
    val name: String,
)

object CollectionNames {
    const val FAVOURITES_NAME = "favourite"
    const val WATCHLIST_NAME = "watchlist"
    const val POPULAR_NAME = "popular"
    const val TOP_RATED_NAME = "top-rated"
    const val IN_THEATRES_NAME = "in-theatres"
}

sealed class CollectionType(val name: String) {
    object Favourite: CollectionType(FAVOURITES_NAME)
    object Watchlist: CollectionType(WATCHLIST_NAME)
    object Popular: CollectionType(POPULAR_NAME)
    object TopRated: CollectionType(TOP_RATED_NAME)
    object InTheatres: CollectionType(IN_THEATRES_NAME)
}