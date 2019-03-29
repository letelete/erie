package io.erie.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsApiDb : RoomDatabase() {
    companion object {
        fun create(context: Context): NewsApiDb {
            val databaseBuilder = Room
                .databaseBuilder(context, NewsApiDb::class.java, "newsapi.db")
            return databaseBuilder.build()
        }
    }

    abstract fun articleDao(): ArticleDao
}