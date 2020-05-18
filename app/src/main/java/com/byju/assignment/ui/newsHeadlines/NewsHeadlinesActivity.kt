package com.byju.assignment.ui.newsHeadlines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.byju.assignment.R
import com.byju.assignment.model.Article
import kotlinx.android.synthetic.main.activity_news_headlines.*

class NewsHeadlinesActivity : AppCompatActivity(), NewsHeadlinesView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_headlines)
    }

    override fun showArticles(articles: List<Article>) {
        articlesRecyclerView.layoutManager = LinearLayoutManager(this)
        articlesRecyclerView.adapter = NewsHeadlinesAdapter(this, articles)
    }
}
