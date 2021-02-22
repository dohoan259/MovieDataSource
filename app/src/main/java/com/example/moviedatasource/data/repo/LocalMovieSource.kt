package com.example.moviedatasource.data.repo

import com.example.moviedatasource.data.local.ActorDao
import com.example.moviedatasource.data.local.CollectionDao
import com.example.moviedatasource.data.local.MovieDao
import com.example.moviedatasource.data.model.Actor
import com.example.moviedatasource.data.model.Cast
import com.example.moviedatasource.data.model.CollectionType
import com.example.moviedatasource.data.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.switchMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMovieSource @Inject constructor(
    private val movieDao: MovieDao,
    private val actorDao: ActorDao,
    private val collectionDao: CollectionDao
) : MovieSource {
    override suspend fun getMovieDetails(movieId: Int): Flow<Movie> {
        return movieDao.getMovie(movieId = movieId)
    }

    override suspend fun saveMovie(movie: Movie) {
        movieDao.saveMovie(movie = movie)
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.saveMovies(movies = movies.toTypedArray())
    }

    override suspend fun getActorsInMovie(movieId: Int): Flow<List<Actor>> {
        return movieDao.getActorsForMovie(movieId = movieId)
    }

    override suspend fun saveCast(cast: Cast) {
        movieDao.saveCast(cast)
        cast.castMembers?.let {
            actorDao.saveActors(*it.toTypedArray())
        }
    }

    override suspend fun isMovieInDatabase(id: Int) = movieDao.isMovieInDatabase(id)

    override suspend fun isCollectionInDatabase(type: CollectionType) =
        collectionDao.isCollectionInDatabase(type.name)

    @ExperimentalCoroutinesApi
    override suspend fun getCollection(type: CollectionType): Flow<List<Movie>> {
        return when (type) {
            CollectionType.Popular ->
                collectionDao.getPopularCollection()
                    .flatMapLatest { collection ->
                        collectionDao.getMoviesForCollection(collection.contents)
                    }
            else ->
                collectionDao.getPopularCollection()
                    .flatMapLatest { collection ->
                        collectionDao.getMoviesForCollection(collection.contents)
                    }
        }
    }
}