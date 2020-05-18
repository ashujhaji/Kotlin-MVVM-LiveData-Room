package com.byju.assignment.di

import com.byju.assignment.ui.newsHeadlines.NewsHeadlinesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        NewsHeadlinesViewModel()
    }
}