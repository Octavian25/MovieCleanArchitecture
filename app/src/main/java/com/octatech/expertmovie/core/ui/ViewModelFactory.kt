package com.octatech.expertmovie.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.octatech.expertmovie.core.di.Injection
import com.octatech.expertmovie.core.domain.usecase.MovieUseCase
import com.octatech.expertmovie.ui.detail.DetailMoviesViewModel
import com.octatech.expertmovie.ui.favorite.FavoriteViewModel
import com.octatech.expertmovie.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val movieuseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance
                        ?: ViewModelFactory(
                            Injection.provideMovieUseCase(
                                context
                            )
                        )
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(movieuseCase) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                DetailMoviesViewModel(movieuseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(movieuseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}