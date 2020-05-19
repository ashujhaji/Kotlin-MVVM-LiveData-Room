package com.byju.assignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.byju.assignment.model.Article

@Dao
interface RoomDao {

    //------------------top headlines-------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<Article>)

    @Query("SELECT * FROM ArticleTable")
    fun loadArticles(): LiveData<List<Article>>

    @Query("DELETE FROM ArticleTable")
    fun clearArticles()

}