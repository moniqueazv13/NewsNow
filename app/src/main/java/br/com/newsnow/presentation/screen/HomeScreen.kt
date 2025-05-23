package br.com.newsnow.presentation.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.newsnow.presentation.NewsArticlesScreen
import br.com.newsnow.presentation.viewmodel.NewsViewModel
import com.kwabenaberko.newsapilib.models.Article
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(newsViewModel: NewsViewModel = koinViewModel(), navController: NavHostController) {

    val articles by newsViewModel.articles.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        CategoriesBar(newsViewModel)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articles) {
                ArticleItem(it, navController)
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, navController: NavHostController) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { navController.navigate(NewsArticlesScreen(article.url)) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(text = article.title, fontWeight = FontWeight.Bold, maxLines = 3)
            article.description?.let { Text(text = it, maxLines = 10) }
            Text(text = article.source.name, maxLines = 1, fontSize = 14.sp)
        }
    }
}

@Composable
fun CategoriesBar(newsViewModel: NewsViewModel) {

    val categories =
        listOf("General", "Business", "Entertainment", "Health", "Science", "Sports", "Technology")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        categories.forEach {
            Button(
                onClick = { newsViewModel.fetchNews(it) },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = it)
            }
        }
    }
}