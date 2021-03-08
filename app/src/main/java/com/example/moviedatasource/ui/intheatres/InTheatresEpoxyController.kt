package com.example.moviedatasource.ui.intheatres

import android.os.Handler
import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.ui.UIState
import com.example.moviedatasource.ui.common.EpoxyCallbacks
import com.example.moviedatasource.ui.common.header
import com.example.moviedatasource.ui.common.infoText
import com.example.moviedatasource.ui.common.movie

class InTheatresEpoxyController(
    private val callbacks: EpoxyCallbacks,
    private val glide: RequestManager,
    epoxyHandler: Handler
) : TypedEpoxyController<UIState.InTheatresScreenState>(epoxyHandler, epoxyHandler) {

    override fun buildModels(state: UIState.InTheatresScreenState) {
        with(state) {
            header {
                id("in-theatres-header")
                title(state.countryName)
                spanSizeOverride { totalSpanCount, _, _ ->
                    totalSpanCount
                }
            }

            when (inTheatresMoviesResource) {
                is Resource.Success -> {
                    when {
                        inTheatresMoviesResource.data?.isEmpty() == true ->
                            infoText {
                                id("in-theatres-info")
                                text("We can't find any movies in theatres near you")
                                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }

                            }
                        else -> inTheatresMoviesResource.data?.forEach { inTheatresMovie ->
                            movie {
                                id(inTheatresMovie.movieId)
                                glide(glide)
                                posterUrl(inTheatresMovie.posterPath)
                                transitionName("poster-${inTheatresMovie.movieId}")
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
                is Resource.Error -> {
                    infoText {
                        id("error-in-theatres-movies")
                        text("Error getting Movies in Theatres")
                        spanSizeOverride { totalSpanCount, _, _ ->
                            totalSpanCount
                        }
                    }
                }
                is Resource.Loading -> {
                    infoText {
                        id("load-in-theatre-movies")
                        text("Loading In-Theatre movies")
                        spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
                    }
                }
            }
        }
    }
}