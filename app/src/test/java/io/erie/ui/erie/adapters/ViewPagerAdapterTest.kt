package io.erie.ui.erie.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import io.erie.ui.allarticles.AllArticlesFragment
import io.erie.ui.topheadlines.TopHeadlinesFragment
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ViewPagerAdapterTest {

    @Mock
    lateinit var fragmentManager: FragmentManager

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewPagerAdapter = ViewPagerAdapter(fragmentManager)
    }

    @Test
    fun `should return TopHeadlinesFragment item`() {
        val position = 0
        assert(fragmentsAreEqual(viewPagerAdapter.getItem(position), TopHeadlinesFragment()))
    }

    @Test
    fun `should return AllArticlesFragment item`() {
        val position = 1
        assert(fragmentsAreEqual(viewPagerAdapter.getItem(position), AllArticlesFragment()))
    }

    private fun fragmentsAreEqual(f1: Fragment, f2: Fragment) =
        f1.javaClass.name == f2.javaClass.name

    @Test
    fun `should return size of fragments list`() {
        val size = 2
        assertEquals(viewPagerAdapter.count, size)
    }

    @Test
    fun `should return null`() {
        assertNull(viewPagerAdapter.getPageTitle(666))
    }
}