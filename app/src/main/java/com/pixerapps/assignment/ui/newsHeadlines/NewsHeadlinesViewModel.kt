package com.pixerapps.assignment.ui.newsHeadlines

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.pixerapps.assignment.data.local.AppDataBase
import com.pixerapps.assignment.data.remote.ApiClient
import com.pixerapps.assignment.data.remote.NetworkException
import com.pixerapps.assignment.data.remote.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

class NewsHeadlinesViewModel : ViewModel() {

    fun getTopHeadlines(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data =
                    ResponseHandler(ApiClient(context).getTopHeadlines()).handleResponse(context)
                if (data != null) {
                    val db = AppDataBase.getAppDatabase(context).roomDao()
                    db.clearArticles()
                    db.insertArticles(data.articles)
                   // topHeadlines.postValue(data.articles)
                }
            } catch (e: NetworkException) {
                Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
            }
        }
    }

}