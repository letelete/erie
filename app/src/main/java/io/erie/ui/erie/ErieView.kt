package io.erie.ui.erie

import io.erie.base.BaseView

interface ErieView : BaseView {
    fun changeToolbarTitle(pagePosition: Int)
    fun setTopHeadlinesColor()
    fun setAllArticlesColor()
    fun updateThemeSelector(themeSelector: Int)
    fun setLightStatusBar()
    fun clearLightStatusBar()
    fun showTopHeadlinesFilterDialog()
    fun showAllArticlesFilterDialog()
}