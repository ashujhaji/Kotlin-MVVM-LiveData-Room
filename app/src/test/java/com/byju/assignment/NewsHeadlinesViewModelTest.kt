package com.byju.assignment

import android.content.Context
import com.byju.assignment.ui.newsHeadlines.NewsHeadlinesViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


class NewsHeadlinesViewModelTest {
    private val newsHeadlinesViewModel = NewsHeadlinesViewModel()
    lateinit var context: Context

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        context = mock(Context::class.java)

    }


    @Test
    fun getTopHeadlines() {
        newsHeadlinesViewModel.getTopHeadlines(context)
    }

}