package com.octatech.expertmovie.ui.favorite

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
import com.octatech.expertmovie.databinding.FragmentFavoriteBinding
import com.octatech.expertmovie.databinding.FragmentHomeBinding
import com.octatech.expertmovie.ui.detail.DetailActivityMovie
import com.octatech.expertmovie.ui.home.HomeViewModel

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.FavoriteMovie.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    movieAdapter.setData(movie)
                }
            })
            favoriteViewModel.FavoriteSeries.observe(viewLifecycleOwner, { series ->
                if (series != null) {
                    seriesAdapter.setData(series)
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