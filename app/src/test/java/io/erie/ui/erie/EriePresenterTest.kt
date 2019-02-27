package io.erie.ui.erie

import io.erie.R
import io.erie.commons.PreferencesManager
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EriePresenterTest {

    @Mock
    lateinit var view: ErieView

    @Mock
    lateinit var testPreferencesManager: PreferencesManager

    private lateinit var presenter: EriePresenter

    private val themesCollection: List<Int> = listOf(
        R.style.AppTheme_NoActionBar_Light,
        R.style.AppTheme_NoActionBar_Dark,
        R.style.AppTheme_NoActionBar_Amoled
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EriePresenter(testPreferencesManager)
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

    @Test
    fun `should update view on theme change`() {
        presenter.handleThemeChange(666, themesCollection)
        verify(view).recreateView()
    }

    @Test
    fun `new theme selector should be 0 - edge test case`() {
        val oldThemeSelectorAtMaxIndex = themesCollection.size - 1
        val expectedNewThemeSelector = 0
        presenter.handleThemeChange(oldThemeSelectorAtMaxIndex, themesCollection)
        verify(view).updateThemeSelector(expectedNewThemeSelector)
    }

    @Test
    fun `new theme selector should be 1`() {
        val oldThemeSelector = 0
        val expectedNewThemeSelector = 1
        presenter.handleThemeChange(oldThemeSelector, themesCollection)
        verify(view).updateThemeSelector(expectedNewThemeSelector)
    }

    @Test
    fun `should fetch selector from preferences and pass it to the view`() {
        val expectedValue = 1
        `when`(testPreferencesManager.getInt(anyString(), anyInt())).thenReturn(expectedValue)
        presenter.fetchThemeSelector()
        verify(view).updateThemeSelector(expectedValue)
    }
}