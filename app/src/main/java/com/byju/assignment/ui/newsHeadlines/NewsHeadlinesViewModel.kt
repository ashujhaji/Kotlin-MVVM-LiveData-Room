package com.byju.assignment.ui.newsHeadlines

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byju.assignment.data.remote.ApiClient
import com.byju.assignment.data.remote.NetworkException
import com.byju.assignment.data.remote.ResponseHandler
import com.byju.assignment.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsHeadlinesViewModel : ViewModel() {

    val topHeadlines = MutableLiveData<List<Article>>()

    fun getTopHeadlines(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data =
                    ResponseHandler(ApiClient(context).getTopHeadlines()).handleResponse(context)
                if (data != null) {
                    topHeadlines.postValue(data.articles)
                }
            } catch (e: NetworkException) {
                Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
            }
        }
    }

}