package io.erie.ui.erie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.erie.R
import io.erie.framework.checkIsSameText
import io.erie.framework.checkIsVisible
import io.erie.framework.performClick
import io.erie.framework.performClickWithContentDescription
import junit.framework.Assert.assertNotSame
import kotlinx.android.synthetic.main.content_main.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ErieActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule<ErieActivity>(ErieActivity::class.java)

    @Test
    fun allViewsShouldBeVisible() {
        R.id.viewPager_erie.checkIsVisible()
        R.id.tablayout_erie_tabs.checkIsVisible()
        R.id.floatingactionbutton_erie_filters.checkIsVisible()
        R.id.textview_erie_title.checkIsVisible()
        R.id.bottomAppBar_erie.checkIsVisible()
    }

    @Test
    fun allViewsShouldBeHidden() {
        assertThat(rule.activity.allArticlesFilterDialogFragment.isVisible, `is`(false))
        assertThat(rule.activity.topHeadlinesFilterDialogFragment.isVisible, `is`(false))
    }

    @Test
    fun verifyDefaultConfiguration() {

        // Title should be dependent on current tab name
        R.id.textview_erie_title.checkIsSameText("Top Headlines")

        // Default tab layout position is 0
        assertThat(rule.activity.tablayout_erie_tabs.selectedTabPosition, `is`(0))
    }

    @Test
    fun themeShouldChange() {
        val previousTheme = rule.activity.theme

        // Clicking bottom app bar button responsible for changing theme
        // This button is a home button so we are using content description instead of simple withId
        R.string.abc_action_bar_up_description.performClickWithContentDescription()

        val newTheme = rule.activity.theme
        assertNotSame(previousTheme, newTheme)
    }

    @Test
    fun shouldOpenTopHeadlinesFiltersDialog() {
        // Switching to the top headlines tab, because filter dialog is different for each tab
        // First we need to set custom tag, to make it possible for espresso finding the view
        val tag = "Top Headlines"
        val topHeadlinesIndex = 0
        rule.activity.tablayout_erie_tabs.getTabAt(topHeadlinesIndex)?.contentDescription = tag
        tag.performClickWithContentDescription()

        // Clicking fab button responsible for opening filters dialog
        R.id.floatingactionbutton_erie_filters.performClick()

        // We want only top headlines dialog to be visible
        assertThat(rule.activity.topHeadlinesFilterDialogFragment.isVisible, `is`(true))
        // So we can additionally check if rest are hidden
        assertThat(rule.activity.allArticlesFilterDialogFragment.isVisible, `is`(false))
    }

    @Test
    fun shouldOpenAllArticlesFiltersDialog() {
        // Switching to the all articles tab, because filter dialog is different for each tab
        // First we need to set contentDescription, so we can use withContentDescription matcher
        val tag = "All Articles"
        val allArticlesIndex = 1
        rule.activity.tablayout_erie_tabs.getTabAt(allArticlesIndex)?.contentDescription = tag
        tag.performClickWithContentDescription()

        // Clicking fab button responsible for opening filters dialog
        R.id.floatingactionbutton_erie_filters.performClick()

        // We want only all articles dialog to be visible
        assertThat(rule.activity.allArticlesFilterDialogFragment.isVisible, `is`(true))
        // So we can additionally check if rest are hidden
        assertThat(rule.activity.topHeadlinesFilterDialogFragment.isVisible, `is`(false))
    }
}