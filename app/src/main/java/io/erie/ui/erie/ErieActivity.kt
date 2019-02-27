package io.erie.ui.erie

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.tabs.TabLayout
import io.erie.R
import io.erie.base.BaseActivity
import io.erie.commons.extensions.getColorFromAttr
import io.erie.ui.erie.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_erie.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


class ErieActivity : BaseActivity<EriePresenter>(), ErieView {

    @Inject
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override var themeSelector: Int = 0
    private var pagePosition: Int = 0
    private var currentAccentColor: Int? = null

    override fun getLayout(): Int = R.layout.activity_erie

    override fun initializeThemeSelector() {
        presenter.fetchThemeSelector()
    }

    override fun initialize() {
        setSupportActionBar(bottomAppBar_erie)
        viewPager_erie.adapter = viewPagerAdapter
        configureTabLayout()
        presenter.handleTabSelected(pagePosition)
    }

    private fun configureTabLayout() {
        tablayout_erie_tabs.apply {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    pagePosition = tab!!.position
                    presenter.handleTabSelected(pagePosition)
                }
            })
            setupWithViewPager(viewPager_erie)
        }
        for (tabIndex in 0 until viewPagerAdapter.count) {
            tablayout_erie_tabs.getTabAt(tabIndex)?.setIcon(viewPagerAdapter.pagesIcons[tabIndex])
        }
    }

    override fun changeToolbarTitle(pagePosition: Int) {
        val titleResId: Int = viewPagerAdapter.pagesTitles[pagePosition]
        textview_erie_title.text = getString(titleResId)
    }

    override fun setTopHeadlinesColor() {
        currentAccentColor = getColorFromAttr(R.attr.themeTopHeadlinesAccentColor)
        changeAccentColor()
    }

    override fun setAllArticlesColor() {
        currentAccentColor = getColorFromAttr(R.attr.themeAllArticlesAccentColor)
        changeAccentColor()
    }

    private fun changeAccentColor() {
        val colorStateList = ColorStateList.valueOf(currentAccentColor!!)
        floatingactionbutton_erie_filters.apply {
            backgroundTintList = colorStateList
        }
        tablayout_erie_tabs.apply {
            tabRippleColor = colorStateList
            setSelectedTabIndicatorColor(currentAccentColor!!)
        }

        for (tabIndex in 0 until viewPagerAdapter.count) {
            tablayout_erie_tabs.getTabAt(tabIndex)?.icon?.setColorFilter(
                getTabIconColor(tabIndex, currentAccentColor!!),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun getTabIconColor(tabIndex: Int, currentAccentColor: Int) =
        if (tabIndex != pagePosition) {
            getColorFromAttr(R.attr.themeTopBarIconTintColor)
        } else {
            currentAccentColor
        }


    override fun updateThemeSelector(themeSelector: Int) {
        this.themeSelector = themeSelector
    }

    override fun recreateView() {
        recreate()
    }

    override fun setLightStatusBar() {
        var flags = window.decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.decorView.systemUiVisibility = flags
    }

    override fun clearLightStatusBar() {
        var flags = window.decorView.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        window.decorView.systemUiVisibility = flags
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottomappbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search -> {
            } // TODO
            R.id.action_scrollToTop -> {
            } // TODO
            android.R.id.home -> {
                presenter.handleThemeChange(
                    oldThemeSelector = themeSelector,
                    availableThemes = themes
                )
            }
        }
        return true
    }
}