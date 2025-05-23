package br.com.newsnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.newsnow.presentation.HomePageScreen
import br.com.newsnow.presentation.NewsArticlesScreen
import br.com.newsnow.presentation.screen.HomeScreen
import br.com.newsnow.presentation.screen.NewsArticleScreen
import br.com.newsnow.presentation.ui.theme.NewsNowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            NewsNowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()) {
                        Text(
                            text = "News Now",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.Red,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                        NavHost(navController = navController, startDestination = HomePageScreen) {
                            composable<HomePageScreen> {
                                HomeScreen(navController = navController)
                            }
                            composable<NewsArticlesScreen> {
                                val args = it.toRoute<NewsArticlesScreen>()
                                NewsArticleScreen(args.url)
                            }
                        }
                    }
                }
            }
        }
    }
}
