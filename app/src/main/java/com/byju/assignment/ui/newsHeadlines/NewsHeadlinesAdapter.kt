package com.byju.assignment.ui.newsHeadlines

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.byju.assignment.R
import com.byju.assignment.databinding.ArticleBinding
import com.byju.assignment.model.Article
import com.byju.assignment.utils.Config

class NewsHeadlinesAdapter(
    private val context: Context,
    private val articles: List<Article>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_article, parent, false
        ) as ArticleBinding
        return ArticleHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleHolder -> holder.bind(context, articles[position])
        }
    }


    class ArticleHolder(private val binding: ArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, article: Article) {
            binding.article = article

            Config.urlToImageView(binding.imageView,article.urlToImage,context)
            binding.publishedAt.text = article.publishedAt.substring(0,10)
        }
    }
}