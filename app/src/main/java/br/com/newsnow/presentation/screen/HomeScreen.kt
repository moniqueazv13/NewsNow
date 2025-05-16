package br.com.newsnow.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.newsnow.presentation.viewmodel.NewsViewModel
import com.kwabenaberko.newsapilib.models.Article
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(newsViewModel: NewsViewModel = koinViewModel()) {
    val articles by newsViewModel.articles.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articles) {
                ArticleItem(it)
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            Text(text = article.title, fontWeight = FontWeight.Bold, maxLines = 3)
            article.description?.let { Text(text = it, maxLines = 10)  }
            Text(text = article.source.name, maxLines = 1, fontSize = 14.sp)
        }
    }
}