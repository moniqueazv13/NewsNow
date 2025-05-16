package br.com.newsnow.presentation.di

import br.com.newsnow.presentation.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { NewsViewModel() }
}

val applicationModules =
    listOf(
        appModule
    )