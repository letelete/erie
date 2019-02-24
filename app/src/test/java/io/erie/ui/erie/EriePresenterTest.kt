package io.erie.ui.erie

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EriePresenterTest {

    @Mock
    lateinit var view: ErieView

    private lateinit var presenter: EriePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EriePresenter()
        presenter.attachView(view)
    }

    @Test
    fun `should setup TopHeadlines`() {
        val position = 0
        presenter.handleTabSelected(position)
        verify(view).changeToolbarTitle(position)
        verify(view).setTopHeadlinesColor()
    }

    @Test
    fun `should setup AllArticles`() {
        val position = 1
        presenter.handleTabSelected(position)
        verify(view).changeToolbarTitle(position)
        verify(view).setAllArticlesColor()
    }
}