package br.com.newsnow.presentation.screen

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NewsArticleScreen(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            loadUrl(url)
        }
    })
}