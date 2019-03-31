package io.erie.ui.erie

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.tabs.TabLayout
import io.erie.R
import io.erie.base.BaseActivity
import io.erie.commons.extensions.getColorFromAttr
import io.erie.ui.allarticles.AllArticlesFilterDialogFragment
import io.erie.ui.allarticles.AllArticlesFragment
import io.erie.ui.erie.adapters.ViewPagerAdapter
import io.erie.ui.topheadlines.TopHeadlinesFilterDialogFragment
import io.erie.ui.topheadlines.TopHeadlinesFragment
import kotlinx.android.synthetic.main.activity_erie.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


class ErieActivity : BaseActivity<EriePresenter>(), ErieView {

    @Inject lateinit var viewPagerAdapter: ViewPagerAdapter
    @Inject lateinit var topHeadlinesFragment: TopHeadlinesFragment
    @Inject lateinit var allArticlesFragment: AllArticlesFragment
    @Inject lateinit var topHeadlinesFilterDialogFragment: TopHeadlinesFilterDialogFragment
    @Inject lateinit var allArticlesFilterDialogFragment: AllArticlesFilterDialogFragment

    private val tabLayoutListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabSelected(tab: TabLayout.Tab?) {
            pagePosition = tab?.position ?: 0
            presenter.handleTabSelected(pagePosition)
        }
    }

    override var themeSelector: Int = 0
    private var pagePosition: Int = 0

    override fun getLayout(): Int = R.layout.activity_erie

    override fun initializeThemeSelector() {
        presenter.fetchThemeSelector()
    }

    override fun initialize() {
        setSupportActionBar(bottomAppBar_erie)
        setupTabLayout()
        setupDialogListeners()
        setupFloatingActionButton()
        presenter.handleTabSelected(pagePosition)
    }

    private fun setupTabLayout() {
        viewPagerAdapter.apply {
            addFragment(
                topHeadlinesFragment,
                R.drawable.ic_topbar_topheadlines,
                R.string.top_headlines
            )
            addFragment(
                allArticlesFragment,
                R.drawable.ic_topbar_allarticles,
                R.string.all_articles
            )
        }
        viewPager_erie.adapter = viewPagerAdapter
        tablayout_erie_tabs.apply {
            addOnTabSelectedListener(tabLayoutListener)
            setupWithViewPager(viewPager_erie)
        }
        for (tabIndex in 0 until viewPagerAdapter.count) {
            tablayout_erie_tabs.getTabAt(tabIndex)?.apply {
                setIcon(viewPagerAdapter.pageIconRes[tabIndex])
            }
        }
    }

    private fun setupDialogListeners() {
        topHeadlinesFilterDialogFragment.listener = topHeadlinesFragment
        allArticlesFilterDialogFragment.listener = allArticlesFragment
    }

    private fun setupFloatingActionButton() {
        floatingactionbutton_erie_filters.setOnClickListener {
            presenter.handleFilterButtonClicked(pagePosition)
        }
    }

    override fun changeToolbarTitle(pagePosition: Int) {
        val titleResId: Int = viewPagerAdapter.pageTitleRes[pagePosition]
        textview_erie_title.text = getString(titleResId)
    }

    override fun setTopHeadlinesColor() {
        val color = getColorFromAttr(R.attr.themeTopHeadlinesAccentColor)
        changeAccentColor(color)
    }

    override fun setAllArticlesColor() {
        val color = getColorFromAttr(R.attr.themeAllArticlesAccentColor)
        changeAccentColor(color)
    }

    private fun changeAccentColor(color: Int) {
        val colorStateList = ColorStateList.valueOf(color)
        floatingactionbutton_erie_filters.apply {
            backgroundTintList = colorStateList
        }
        tablayout_erie_tabs.apply {
            tabRippleColor = colorStateList
            setSelectedTabIndicatorColor(color)
        }

        for (tabIndex in 0 until viewPagerAdapter.count) {
            val tabColor = when {
                tabIndex != pagePosition -> getColorFromAttr(R.attr.themeTopBarIconTintColor)
                else -> color
            }
            tablayout_erie_tabs.getTabAt(tabIndex)?.icon?.setColorFilter(
                tabColor,
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    override fun updateThemeSelector(themeSelector: Int) {
        this.themeSelector = themeSelector
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setLightStatusBar() {
        var flags = window.decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.decorView.systemUiVisibility = flags
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun clearLightStatusBar() {
        var flags = window.decorView.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        window.decorView.systemUiVisibility = flags
    }

    override fun showTopHeadlinesFilterDialog() {
        topHeadlinesFilterDialogFragment.apply {
            if (!isVisible) {
                show(supportFragmentManager, tag)
            }
        }
    }

    override fun showAllArticlesFilterDialog() {
        allArticlesFilterDialogFragment.apply {
            if (!isVisible) {
                show(supportFragmentManager, tag)
            }
        }
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