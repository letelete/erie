package io.erie.repository.mapper

import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article
import io.erie.model.responses.Source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ArticleMapperTest {
    private val article: Article by lazy {
        Article(
            source = Source(
                name = "name",
                id = "id"
            ),
            title = "title",
            urlToImage = "urlToImage",
            publishedAt = "2019-03-27T05:52:21Z",
            description = "description",
            content = "content",
            author = "author",
            url = "url"
        )
    }
    private val expectedMappedArticle by lazy {
        ArticleEntity(
            thumbnailUrl = "urlToImage",
            title = "title",
            authorName = "author",
            sourceName = "name",
            publishedAt = "2019-03-27T05:52:21Z",
            readTime = "TODO",
            articleUrl = "url"
        )
    }

    @Test
    fun `all essential attributes are specified`() {
        assertThat(article.hasEssentialAttributes(), `is`(true))
    }

    @Test
    fun mapFromResponse() {
        val mappedArticle = ArticleMapper.mapFromResponse(article)
        assertThat(mappedArticle, `is`(expectedMappedArticle))
    }
}