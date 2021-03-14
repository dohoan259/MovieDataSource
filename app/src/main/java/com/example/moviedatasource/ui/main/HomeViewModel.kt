package com.example.moviedatasource.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.local.entity.CollectionType
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.repo.MovieRepo
import com.example.moviedatasource.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    private val _viewState = MutableLiveData<UIState.HomeScreenState>()
    val viewState: LiveData<UIState.HomeScreenState> = _viewState

    init {
        viewModelScope.launch {
            combine(
                listOf(
                    getPopularMovies(),
                    getTopRatedMovies()
                )
            ) { arrayOfViewState ->
                UIState.HomeScreenState(
                    popularMoviesResource = arrayOfViewState[0],
                    topRatedMoviesResource = arrayOfViewState[1],
                    searchResultsResource = null
                )
            }.conflate()
                .flowOn(Dispatchers.Default)
                .onEach { viewState ->
                    // Updating view state
                    _viewState.value = viewState
                }
                .collect()
        }
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return movieRepo.getCollection(CollectionType.Popular)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>> {
        return movieRepo.getCollection(CollectionType.TopRated)
    }

    fun searchMovie(query: String) = movieRepo.getSearchResultForQuery(query = query).onEach {
        _viewState.value =
            _viewState.value?.popularMoviesResource?.let { it1 ->
                _viewState.value?.topRatedMoviesResource?.let { it2 ->
                    UIState.HomeScreenState(
                        popularMoviesResource = it1,
                        topRatedMoviesResource = it2,
                        searchResultsResource = it
                    )
                }
            }
    }
}