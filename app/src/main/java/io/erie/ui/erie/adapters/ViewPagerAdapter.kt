package io.erie.ui.erie.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.erie.R
import io.erie.ui.allarticles.AllArticlesFragment
import io.erie.ui.topheadlines.TopHeadlinesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    val pages = mutableListOf<Fragment>()
    val pageIconRes = mutableListOf<Int>()
    val pageTitleRes = mutableListOf<Int>()

    fun addFragment(fragment: Fragment, iconResId: Int, titleResId: Int) {
        pages.add(fragment)
        pageIconRes.add(iconResId)
        pageTitleRes.add(titleResId)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.count()

    override fun getPageTitle(position: Int): CharSequence? = null
}