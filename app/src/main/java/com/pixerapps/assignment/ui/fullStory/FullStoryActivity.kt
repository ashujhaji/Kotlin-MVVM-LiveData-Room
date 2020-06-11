package com.pixerapps.assignment.ui.fullStory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.pixerapps.assignment.R
import com.pixerapps.assignment.data.local.AppDataBase
import com.pixerapps.assignment.databinding.FullStoryBinding
import com.pixerapps.assignment.model.Article
import com.pixerapps.assignment.utils.Config
import kotlinx.android.synthetic.main.activity_full_story.*

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