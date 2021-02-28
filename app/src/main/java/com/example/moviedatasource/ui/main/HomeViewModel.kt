package com.example.moviedatasource.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.local.entity.CollectionType
import com.example.moviedatasource.data.model.CollectionWithMovies
import com.example.moviedatasource.data.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getPopularMovies(): LiveData<Resource<CollectionWithMovies>> {
        return movieRepo.getCollection(CollectionType.Popular)
            .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getTopRatedMovies(): LiveData<Resource<CollectionWithMovies>> {
        return movieRepo.getCollection(CollectionType.TopRated)
            .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getMoviesInTheatres(): LiveData<Resource<CollectionWithMovies>> {
        return movieRepo.getCollection(CollectionType.InTheatres)
            .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)
    }
}