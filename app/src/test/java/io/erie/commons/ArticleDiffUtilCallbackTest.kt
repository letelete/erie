package io.erie.commons

import io.erie.commons.utils.ArticleDiffUtilCallback
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article
import io.erie.model.responses.Source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class ArticleDiffUtilCallbackTest {

    private val oldArticle by lazy {
        ArticleEntity(
            thumbnailUrl = "urlToImage0",
            title = "title0",
            authorName = "author0",
            sourceName = "name0",
            publishedAt = "2019-03-27T05:52:21Z",
            readTime = "5 min",
            articleUrl = "url0"
        )
    }

    private val newArticle by lazy {
        ArticleEntity(
            thumbnailUrl = "urlToImage1",
            title = "title1",
            authorName = "author1",
            sourceName = "name1",
            publishedAt = "2019-03-27T05:52:21Z",
            readTime = "3 min",
            articleUrl = "url1"
        )
    }

    private lateinit var diffUtil: ArticleDiffUtilCallback

    @Before
    fun setUp() {
        diffUtil = ArticleDiffUtilCallback()
    }

    @Test
    fun `contents are the same`() {
        val areContentsTheSame = diffUtil
            .areContentsTheSame(oldArticle, oldArticle)
        assertThat(areContentsTheSame, `is`(true))
    }

    @Test
    fun `items not the same`() {
        val areItemsTheSame = diffUtil
            .areItemsTheSame(oldArticle, newArticle)
        assertThat(areItemsTheSame, `is`(false))
    }

    @Test
    fun `contents not the same`() {
        val areContentsTheSame = diffUtil
            .areContentsTheSame(oldArticle, newArticle)
        assertThat(areContentsTheSame, `is`(false))
    }

    @Test
    fun `items are the same`() {
        val areItemsTheSame = diffUtil
            .areItemsTheSame(newArticle, newArticle)
        assertThat(areItemsTheSame, `is`(true))
    }
}