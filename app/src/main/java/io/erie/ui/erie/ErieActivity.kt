package io.erie.ui.erie

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.Menu
import android.view.MenuItem
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

    var pagePosition: Int = 0

    override fun getLayout(): Int = R.layout.activity_erie

    override fun initialize() {
        setSupportActionBar(bottomAppBar_erie)
        viewPager_erie.adapter = viewPagerAdapter
        configureTabLayout()
    }

    private fun configureTabLayout() {
        tablayout_erie_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pagePosition = tab!!.position
                presenter.handleTabSelected(pagePosition)
            }
        })
        tablayout_erie_tabs.setupWithViewPager(viewPager_erie)
        for (tabIndex in 0 until viewPagerAdapter.count) {
            tablayout_erie_tabs.getTabAt(tabIndex)?.setIcon(viewPagerAdapter.pagesIcons[tabIndex])
        }
    }

    override fun changeToolbarTitle(pagePosition: Int) {
        val titleResId: Int = viewPagerAdapter.pagesTitles[pagePosition]
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
            tablayout_erie_tabs.getTabAt(tabIndex)?.icon?.setColorFilter(
                getTabIconColor(tabIndex, color),
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
            } // TODO
        }
        return true
    }
}