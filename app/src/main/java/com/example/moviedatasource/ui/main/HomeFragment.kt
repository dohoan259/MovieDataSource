package com.example.moviedatasource.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviedatasource.R
import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getPopularMovies()
            .observe(this, { popularMovies ->
                when (popularMovies) {
                    is Resource.Loading -> Log.d("hoan.dv", "popular loading: ${popularMovies.data}")
                    is Resource.Success -> Log.d("hoan.dv", "popular success: ${popularMovies.data?.movies?.size}")
                    else ->
                        Log.d("hoan.dv", "popular error: ${popularMovies.message}")
                }
            })
        homeViewModel.getTopRatedMovies()
            .observe(this, { movieInTheatres ->
                when (movieInTheatres) {
                    is Resource.Loading -> Log.d("hoan.dv", "in theatres loading: ${movieInTheatres.data}")
                    is Resource.Success -> Log.d("hoan.dv", "in theatres success: ${movieInTheatres.data?.movies?.size}")
                    else ->
                        Log.d("hoan.dv", "in theatres error: ${movieInTheatres.message}")
                }
            })
//        homeViewModel.getMoviesInTheatres()
//            .observe(this, { topRatedMovie ->
//                when (topRatedMovie) {
//                    is Resource.Loading -> Log.d("hoan.dv", "top rated loading: ${topRatedMovie.data}")
//                    is Resource.Success -> Log.d("hoan.dv", "top rated success: ${topRatedMovie.data?.movies?.size}")
//                    else ->
//                        Log.d("hoan.dv", "top rated error: ${topRatedMovie.message}")
//                }
//            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}