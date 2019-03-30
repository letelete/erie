package io.erie.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "thumbnailUrl")
    val thumbnailUrl: String,
    @ColumnInfo(name = "authorName")
    val authorName: String,
    @ColumnInfo(name = "sourceName")
    val sourceName: String,
    @ColumnInfo(name = "publishedDateTime")
    val publishedAt: String,
    @ColumnInfo(name = "readTime")
    val readTimeInMinutes: Int,
    @ColumnInfo(name = "articleUrl")
    val articleUrl: String,
    @ColumnInfo(name = "fromTopHeadlines")
    var fromTopHeadlines: Boolean = false,
    @ColumnInfo(name = "fromAllArticles")
    var fromAllArticles: Boolean = false
)