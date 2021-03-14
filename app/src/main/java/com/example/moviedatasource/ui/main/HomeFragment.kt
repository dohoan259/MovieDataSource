package com.example.moviedatasource.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.moviedatasource.R
import com.example.moviedatasource.databinding.FragmentHomeBinding
import com.example.moviedatasource.ui.base.BaseFragment
import com.example.moviedatasource.ui.common.EpoxyCallbacks
import com.example.moviedatasource.utils.EqualSpaceGridItemDecoration
import com.example.moviedatasource.utils.getNumberOfColumns
import com.example.moviedatasource.utils.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.roundToInt

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    @ExperimentalCoroutinesApi
    @FlowPreview
    private val homeViewModel: HomeViewModel by viewModels()

    private val callbacks = object : EpoxyCallbacks {
        override fun onMovieItemClicked(id: Int, transitionName: String, sharedView: View?) {
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                movieId = id,
                transitionName = transitionName,
                isAuthenticate = false
            )
            sharedView?.let {
                val extras = FragmentNavigatorExtras(sharedView to transitionName)
                findNavController().navigate(action, extras)
            } ?: findNavController().navigate(action)


//            val action = HomeFragmentDirections.viewMovieDetails(
//                movieIdArg = id,
//                isAuthenticatedArg = mainViewModel.isAuthenticated,
//                transitionNameArg = transitionName
//            )
//            sharedView?.let {
//                val extras = FragmentNavigatorExtras(sharedView to transitionName)
//                findNavController().navigate(action, extras)
//            } ?: findNavController().navigate(action)
        }
    }

    private lateinit var glideRequestManager: RequestManager

    private lateinit var homeEpoxyController: HomeEpoxyController

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glideRequestManager = Glide.with(this)
        homeEpoxyController = HomeEpoxyController(
            callbacks = callbacks,
            glide = glideRequestManager,
            EpoxyAsyncUtil.getAsyncBackgroundHandler()
        )

        homeViewModel.viewState.observe(this) { viewState ->
            homeEpoxyController.setData(viewState)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHome.apply {
            val columns =
                resources.getDimension(R.dimen.movie_grid_poster_width).getNumberOfColumns(context)
            val space = resources.getDimension(R.dimen.movie_grid_item_space)
            layoutManager = GridLayoutManager(context, columns)
            addItemDecoration(EqualSpaceGridItemDecoration(space.roundToInt()))
            setController(homeEpoxyController)
        }

        setupSearchBox()
        getView()?.requestFocus()
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    private fun setupSearchBox() {
        binding.searchBox.etSearchBox.textChanges()
//            .distinctUntilChanged()
            .filterNot { it.isNullOrBlank() }
//            .debounce(300)
            .flatMapLatest {
                homeViewModel.searchMovie(it!!.toString())
            }
            .onEach {
                //todo: update UI
            }
            .launchIn(lifecycleScope)

    }
}