package br.com.newsnow

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { NewsViewModel() }
}

val applicationModules =
    listOf(
        appModule
    )