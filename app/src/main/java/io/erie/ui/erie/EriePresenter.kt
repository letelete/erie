package io.erie.ui.erie

import io.erie.base.BasePresenter
import io.erie.commons.PreferencesManager
import io.erie.commons.PreferencesManagerImpl.Companion.KEY_THEME

class EriePresenter(val preferencesManager: PreferencesManager) :
    BasePresenter<ErieView>() {

    fun handleTabSelected(position: Int) {
        when (position) {
            0 -> {
                getView()?.setTopHeadlinesColor()
            }
            1 -> {
                getView()?.setAllArticlesColor()
            }
        }
        getView()?.changeToolbarTitle(position)
    }

    fun handleThemeChange(oldThemeSelector: Int, availableThemes: List<Int>) {
        val maxIndex = availableThemes.size - 1
        val isOldThemeSelectorOnEdge = oldThemeSelector >= maxIndex || oldThemeSelector < 0
        val newSelector = if (isOldThemeSelectorOnEdge) {
            0
        } else {
            oldThemeSelector + 1
        }
        preferencesManager.putInt(KEY_THEME, newSelector)
        getView()?.updateThemeSelector(newSelector)
        getView()?.recreateView()
    }

    fun fetchThemeSelector() {
        val selector = preferencesManager.getInt(KEY_THEME, 0)
        getView()?.updateThemeSelector(selector)
    }
}