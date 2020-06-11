package com.byju.assignment.ui.newsHeadlines

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.byju.assignment.R
import com.byju.assignment.databinding.ArticleBinding
import com.byju.assignment.model.Article
import com.byju.assignment.ui.fullStory.FullStoryActivity
import com.byju.assignment.utils.Config

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
            binding.root.setOnClickListener {
                val intent = Intent(context,FullStoryActivity::class.java)
                intent.putExtra("pos",adapterPosition)
                context.startActivity(intent)
            }
        }
    }
}