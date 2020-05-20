package com.byju.assignment.ui.fullStory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.byju.assignment.R
import com.byju.assignment.data.local.AppDataBase
import com.byju.assignment.databinding.FullStoryBinding
import com.byju.assignment.model.Article
import com.byju.assignment.utils.Config
import kotlinx.android.synthetic.main.activity_full_story.*

class FullStoryActivity : AppCompatActivity() {

    private lateinit var binding : FullStoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_story)

        val pos = intent.getIntExtra("pos",0)
        AppDataBase.getAppDatabase(this).roomDao().loadArticles().observe(this, Observer {
            showStory(it[pos])
        })
        back.setOnClickListener {
            finish()
        }
    }

    private fun showStory(article: Article){
        binding.article = article
        Config.urlToImageView(binding.imageView,article.urlToImage,this)
        binding.publishedAt.text = article.publishedAt.substring(0,10)
    }
}