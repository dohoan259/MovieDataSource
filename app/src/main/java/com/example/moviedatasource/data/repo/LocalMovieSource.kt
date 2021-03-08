package com.example.moviedatasource.data.repo

import androidx.room.Transaction
import com.example.moviedatasource.data.local.ActorDao
import com.example.moviedatasource.data.local.CollectionDao
import com.example.moviedatasource.data.local.MovieDao
import com.example.moviedatasource.data.local.entity.*
import com.example.moviedatasource.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMovieSource @Inject constructor(
    private val movieDao: MovieDao,
    private val actorDao: ActorDao,
    private val collectionDao: CollectionDao
) : MovieSource {

    override suspend fun getAllMovies(): Flow<List<Movie>> {
        return movieDao.getAllMovies()
    }

    override fun getMovieDetails(movieId: Int): Flow<MovieDetail> {
        return movieDao.getMovieDetail(movieId = movieId)
    }

    override suspend fun saveMovie(movie: Movie) {
        movieDao.saveMovie(movie = movie)
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.saveMovies(movies = movies.toTypedArray())
    }

    @Transaction
    override suspend fun saveMoviesForCollection(collection: MovieCollection, movies: List<Movie>) {
        collectionDao.saveCollection(collection)
        val movieIds = movieDao.saveMovies(movies = movies.toTypedArray())
        val movieCollections = ArrayList<MovieCollectionCrossRef>()
        for (movieId in movieIds) {
            val movieCollectionCrossRef = MovieCollectionCrossRef(
                movieId = movieId.toInt(),
                collectionId = collection.name
            )
            movieCollections.add(movieCollectionCrossRef)
        }
        val collectionRefs = movieDao.saveAllCollectionCross(*movieCollections.toTypedArray())
    }

//    override suspend fun getActorsInMovie(movieId: Int): Flow<List<Actor>> {
//        return movieDao.getActorsForMovie(movieId = movieId)
//    }

    override suspend fun saveCast(cast: Cast) {
        movieDao.saveCast(cast)
//        cast.castMembers?.let {
//            actorDao.saveActors(*it.toTypedArray())
//        }
    }

    override suspend fun isMovieInDatabase(id: Int) = movieDao.isMovieInDatabase(id)

    override suspend fun isCollectionInDatabase(type: CollectionType) =
        collectionDao.isCollectionInDatabase(type.name)

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun getCollectionWithMovie(type: CollectionType): Flow<CollectionWithMovies?> {
        return collectionDao.getCollectionWithMovies(type.name)
    }
}