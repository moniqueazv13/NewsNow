package br.com.newsnow.presentation

import kotlinx.serialization.Serializable

@Serializable
object HomePageScreen

@Serializable
data class NewsArticlesScreen(val url: String)