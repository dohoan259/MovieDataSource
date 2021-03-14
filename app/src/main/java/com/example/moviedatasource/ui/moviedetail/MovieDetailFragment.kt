package com.example.moviedatasource.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.example.moviedatasource.databinding.FragmentMovieDetailBinding
import com.example.moviedatasource.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val safeArgs: MovieDetailFragmentArgs by navArgs()

    private lateinit var glideRequestManager: RequestManager

    @ExperimentalCoroutinesApi
    @FlowPreview
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }
}