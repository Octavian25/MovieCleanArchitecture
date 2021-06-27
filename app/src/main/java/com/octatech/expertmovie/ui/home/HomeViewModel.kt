package com.octatech.expertmovie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.octatech.expertmovie.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
    val series = movieUseCase.getAllSeries().asLiveData()
}