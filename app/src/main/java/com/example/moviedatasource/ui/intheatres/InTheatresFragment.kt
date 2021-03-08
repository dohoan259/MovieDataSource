package com.example.moviedatasource.ui.intheatres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.moviedatasource.R
import com.example.moviedatasource.databinding.FragmentIntheatresBinding
import com.example.moviedatasource.ui.base.BaseFragment
import com.example.moviedatasource.ui.common.EpoxyCallbacks
import com.example.moviedatasource.utils.EqualSpaceGridItemDecoration
import com.example.moviedatasource.utils.getNumberOfColumns
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.math.roundToInt

@AndroidEntryPoint
class InTheatresFragment : BaseFragment() {

    private lateinit var binding: FragmentIntheatresBinding

    @ExperimentalCoroutinesApi
    @FlowPreview
    private val inTheatresViewModel: InTheatresViewModel by viewModels()

    private val callbacks = object : EpoxyCallbacks {
        override fun onMovieItemClicked(id: Int, transitionName: String, sharedView: View?) {
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

    private lateinit var controller: InTheatresEpoxyController

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glideRequestManager = Glide.with(this)
        controller = InTheatresEpoxyController(
            callbacks = callbacks,
            glide = glideRequestManager,
            EpoxyAsyncUtil.getAsyncBackgroundHandler()
        )

        inTheatresViewModel.viewState.observe(this) {
            controller.setData(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntheatresBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.apply {
            val columns =
                resources.getDimension(R.dimen.movie_grid_poster_width).getNumberOfColumns(context)
            val space = resources.getDimension(R.dimen.movie_grid_item_space)
            layoutManager = GridLayoutManager(context, columns)
            addItemDecoration(EqualSpaceGridItemDecoration(space.roundToInt()))
            setController(controller = controller)
        }
    }
}