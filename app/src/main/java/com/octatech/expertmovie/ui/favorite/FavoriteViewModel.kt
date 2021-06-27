package com.octatech.expertmovie.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.octatech.expertmovie.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    var FavoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
    var FavoriteSeries = movieUseCase.getFavoriteSeries().asLiveData()
}