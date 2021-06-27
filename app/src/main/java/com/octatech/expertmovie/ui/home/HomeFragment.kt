package com.octatech.expertmovie.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.octatech.expertmovie.R
import com.octatech.expertmovie.core.source.Resource
import com.octatech.expertmovie.core.ui.MovieAdapter
import com.octatech.expertmovie.core.ui.SeriesAdapter
import com.octatech.expertmovie.core.ui.ViewModelFactory
import com.octatech.expertmovie.databinding.FragmentHomeBinding
import com.octatech.expertmovie.ui.detail.DetailActivityMovie
import com.octatech.expertmovie.ui.detail.DetailActivitySeries
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            val seriesAdapter = SeriesAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivityMovie::class.java)
                intent.putExtra(DetailActivityMovie.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            seriesAdapter.onItemClick = { selectedData ->
//                val intentSeries = Intent(activity, DetailActivitySeries::class.java)
//                intentSeries.putExtra(DetailActivitySeries.EXTRA_DATA, selectedData)
//                startActivity(intentSeries)
            }


            val factory = ViewModelFactory.getInstance(requireActivity())
            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            homeViewModel.movie.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvDetailError.text = movie.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })
            homeViewModel.series.observe(viewLifecycleOwner, { series ->
                Log.e("TRAP SERIES", "onViewCreated: " + series.data )
                if (series != null) {
                    when (series) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            seriesAdapter.setData(series.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvDetailError.text = series.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })


            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false )
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            with(binding.rvSeries) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }
        }
    }
}