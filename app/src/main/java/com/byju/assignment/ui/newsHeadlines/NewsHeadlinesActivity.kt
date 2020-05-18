package com.byju.assignment.ui.newsHeadlines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.byju.assignment.R
import com.byju.assignment.model.Article
import kotlinx.android.synthetic.main.activity_news_headlines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsHeadlinesActivity : AppCompatActivity(), NewsHeadlinesView,
    SwipeRefreshLayout.OnRefreshListener {

    val myViewModel: NewsHeadlinesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_headlines)

        refreshLayout.isRefreshing = true
        myViewModel.getTopHeadlines(this)
        myViewModel.topHeadlines.observe(this, Observer {
            showArticles(it)
        })

        refreshLayout.setOnRefreshListener(this)
    }

    override fun showArticles(articles: List<Article>) {
        refreshLayout.isRefreshing = false
        articlesRecyclerView.layoutManager = LinearLayoutManager(this)
        articlesRecyclerView.adapter = NewsHeadlinesAdapter(this, articles)
    }

    override fun onRefresh() {
        myViewModel.getTopHeadlines(this)
    }
}
