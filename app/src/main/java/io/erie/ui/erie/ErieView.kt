package io.erie.ui.erie

interface ErieView {
    fun changeToolbarTitle(pagePosition: Int)
    fun setTopHeadlinesColor()
    fun setAllArticlesColor()
    fun updateThemeSelector(themeSelector: Int)
    fun recreateView()
    fun setLightStatusBar()
    fun clearLightStatusBar()
}