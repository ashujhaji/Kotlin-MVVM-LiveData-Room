package com.byju.assignment.ui.newsHeadlines

import com.byju.assignment.model.Article

interface NewsHeadlinesView {
    fun showArticles(articles:List<Article>)
}