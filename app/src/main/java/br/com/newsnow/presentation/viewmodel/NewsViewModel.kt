package br.com.newsnow.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.newsnow.presentation.utils.Constant
import br.com.newsnow.presentation.utils.Constant.QUERY
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.NewsApiClient.ArticlesResponseCallback
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse


class NewsViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        fetchNews()
    }

     fun fetchNews(category: String? = QUERY) {
        val newsApiClient = NewsApiClient(Constant.API_KEY)

        newsApiClient.getEverything(
            EverythingRequest.Builder()
                .q(category)
                .build(),
            object : ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {
                    _articles.postValue(response.articles)
                }

                override fun onFailure(throwable: Throwable) {
                    println(throwable.message)
                }
            }
        )
    }
}