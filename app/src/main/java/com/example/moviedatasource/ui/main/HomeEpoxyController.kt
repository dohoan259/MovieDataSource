package com.example.moviedatasource.ui.main

import android.os.Handler
import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.ui.UIState
import com.example.moviedatasource.ui.common.*
import com.example.moviedatasource.utils.safe

class HomeEpoxyController(
    private val callbacks: EpoxyCallbacks,
    private val glide: RequestManager,
    epoxyHandler: Handler
) : TypedEpoxyController<UIState.HomeScreenState>(epoxyHandler, epoxyHandler) {

    override fun buildModels(state: UIState.HomeScreenState) {
        with(state) {
            searchResultsResource?.let {
                buildSearchModel(it)
            } ?: run {
                buildHomeModel(popularMoviesResource, topRatedMoviesResource)
            }
        }
    }

    private fun buildSearchModel(searchResults: Resource<List<Movie>>?) {
        header {
            id("search-results")
            title("Search Results")
            spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
        }

        searchResults?.let {
            when (searchResults) {
                is Resource.Success -> {
                    when {
                        searchResults.data?.isEmpty() == true -> {
                            infoText {
                                id("no-results-found")
                                text("No movies found for this query")
                                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
                            }
                        }
                        else -> searchResults.data?.forEach { searchResult ->
                            movieSearchResult {
                                with(searchResult) {
                                    movieId(movieId)
//                                    movieId(id)
                                    movieTitle(title)
                                    glide(glide)
                                    posterUrl(posterPath)
                                    transitionName("poster-$movieId")
                                    clickListener { model, _, clickedView, _ ->
                                        callbacks.onMovieItemClicked(
                                            model.movieId!!,
                                            model.transitionName,
                                            clickedView
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }.safe
        }
    }

    private fun buildHomeModel(
        popularMovies: Resource<List<Movie>>?,
        topRatedMovies: Resource<List<Movie>>?
    ) {
        header {
            id("popular")
            title("Popular")
            spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
        }

        when (popularMovies) {
            is Resource.Success -> {
                popularMovies.data?.forEach { popularMovie ->
                    movie {
                        id(popularMovie.movieId)
                        movieId(popularMovie.movieId)
                        glide(glide)
                        posterUrl(popularMovie.posterPath)
                        transitionName("poster-${popularMovie.movieId}")
                        clickListener { model, _, clickedView, _ ->
                            callbacks.onMovieItemClicked(
                                model.movieId!!,
                                model.transitionName(),
                                clickedView
                            )
                        }
                    }
                }
            }
            is Resource.Error -> {
                infoText {
                    id("error-popular-movies")
                    text("Error getting Popular movies")
                    spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
                }
            }
            is Resource.Loading -> {
                loading {
                    id("load-popular-movies")
                    description("Loading Popular movies")
                    spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
                }
            }
            null -> Unit
        }.safe

        header {
            id("top-rated")
            title("Top Rated")
            spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
        }

        when (topRatedMovies) {
            is Resource.Success -> {
                topRatedMovies.data?.forEach { topRatedMovie ->
                    movie {
                        id(topRatedMovie.movieId)
                        movieId(topRatedMovie.movieId)
                        glide(glide)
                        posterUrl(topRatedMovie.posterPath)
                        transitionName("poster-${topRatedMovie.movieId}")
                        clickListener { model, _, clickedView, _ ->
                            callbacks.onMovieItemClicked(
                                model.movieId!!,
                                model.transitionName(),
                                clickedView
                            )
                        }
                    }
                }
            }
            is Resource.Error -> {
                infoText {
                    id("error-top-rated-movies")
                    text("Error getting Top Rated movies")
                    spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
                }
            }
            is Resource.Loading -> {
                loading {
                    id("load-top-rated-movies")
                    description("Loading Top Rated movies")
                    spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
                }
            }
            null -> Unit
        }.safe
    }

}