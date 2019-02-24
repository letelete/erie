package io.erie.ui.erie.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.erie.R
import io.erie.ui.allarticles.AllArticlesFragment
import io.erie.ui.topheadlines.TopHeadlinesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private val pages: List<Fragment> = listOf(
        TopHeadlinesFragment(),
        AllArticlesFragment()
    )

    val pagesIcons = intArrayOf(
        R.drawable.ic_topbar_topheadlines,
        R.drawable.ic_topbar_allarticles
    )

    val pagesTitles = intArrayOf(
        R.string.top_headlines,
        R.string.all_articles
    )

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.count()

    override fun getPageTitle(position: Int): CharSequence? = null
}