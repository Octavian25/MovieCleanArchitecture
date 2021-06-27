package com.octatech.expertmovie.core.di

import com.octatech.expertmovie.core.domain.usecase.MovieInteractor
import com.octatech.expertmovie.core.domain.usecase.MovieUseCase
import com.octatech.expertmovie.ui.favorite.FavoriteViewModel
import com.octatech.expertmovie.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}

