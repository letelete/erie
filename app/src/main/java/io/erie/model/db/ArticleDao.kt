package io.erie.model.db

import androidx.paging.DataSource
import androidx.room.*
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles WHERE fromTopHeadlines = 1")
    fun topHeadlinesArticles(): DataSource.Factory<Int, ArticleEntity>

    @Query("SELECT * FROM articles WHERE fromAllArticles = 1")
    fun allArticles(): DataSource.Factory<Int, ArticleEntity>
}