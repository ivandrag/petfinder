package com.petfinder.di

import com.petfinder.presentation.animalDetails.AnimalDetailsViewModel
import com.petfinder.presentation.animalList.AnimalListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AnimalListViewModel(get()) }
    viewModel { AnimalDetailsViewModel(get()) }
}
