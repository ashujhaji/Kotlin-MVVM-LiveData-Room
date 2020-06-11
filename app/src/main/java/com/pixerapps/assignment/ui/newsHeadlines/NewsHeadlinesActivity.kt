package com.pixerapps.assignment.ui.newsHeadlines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pixerapps.assignment.R
import com.pixerapps.assignment.data.local.AppDataBase
import com.pixerapps.assignment.model.Article
import kotlinx.android.synthetic.main.activity_news_headlines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/*MIT License

        Copyright (c) 2020 Ashutosh Jha

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.*/


class NewsHeadlinesActivity : AppCompatActivity(), NewsHeadlinesView,
    SwipeRefreshLayout.OnRefreshListener {

    private val myViewModel: NewsHeadlinesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_headlines)

        refreshLayout.isRefreshing = true
        myViewModel.getTopHeadlines(this)

        AppDataBase.getAppDatabase(this).roomDao().loadArticles().observe(this, Observer {
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
