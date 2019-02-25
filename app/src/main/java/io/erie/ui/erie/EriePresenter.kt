package io.erie.ui.erie

import io.erie.base.BasePresenter

class EriePresenter : BasePresenter<ErieView>() {

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
}