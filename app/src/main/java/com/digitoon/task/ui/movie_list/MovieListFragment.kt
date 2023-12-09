package com.digitoon.task.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.digitoon.task.R
import com.digitoon.task.databinding.FragmentMovieListBinding
import com.google.android.material.transition.MaterialElevationScale
import com.digitoon.task.ui.movie_list.adapter.MovieAdapter
import com.digitoon.task.ui.movie_list.adapter.MovieListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment(), MovieListener {

    private val viewModel by viewModels<MovieListViewModel>()

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val movieAdapter = MovieAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show popup transition animation from MovieDetailsFragment
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.movieListEvents.collect { event ->
                when (event) {
                    MovieListEvents.InitView -> initViews()
                    is MovieListEvents.NavigateToMovieDetails -> navigateToMovieDetails(event.view,event.movieId)
                    is MovieListEvents.SendMessage -> Toast.makeText(requireContext(), event.message, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        viewModel.fragmentCreated()
    }

    private fun navigateToMovieDetails(view : View, movieId: Long) {
        try {
//            findNavController().navigate(
//                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(
//                    movieId  = movieId
//                ),
//                setTransitionStuff(
//                    getString(R.string.movie_details_transition_name),
//                    view
//                )
//            )
        } catch (e: Exception) {

        }
    }

    private fun initViews() {
        initListeners()

        binding.apply {
            toolbarLayout.ivBack.visibility = INVISIBLE
            toolbarLayout.tvTitle.text = getString(R.string.app_name)

            rvMovies.apply {
                adapter = movieAdapter
                itemAnimator = null
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.movieList.collectLatest {
                    movieAdapter.submitList(it)
                }
            }


            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.isLoading.collectLatest {
                    progressCircular.isVisible = it
                }
            }


        }
    }

    private fun initListeners() {
        binding.apply {
            swipeLayout.setOnRefreshListener {
                viewModel.getMovieList()
                swipeLayout.isRefreshing = false
            }
        }
    }

    // Set transition animation
    private fun setTransitionStuff(
        viewTransitionName: String,
        view: View
    ): FragmentNavigator.Extras {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }

        return FragmentNavigatorExtras(view to viewTransitionName)
    }

    override fun onMovieClicked(view: View, movieId: Long) {
        viewModel.onMovieClicked(view, movieId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


