package io.erie.commons.utils

import android.content.Context
import io.erie.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class HumanDateTest {

    private val context = mock(Context::class.java)
    private val humanDate = HumanDate(context)

    @Before
    fun setUp() {
        `when`(context.getString(R.string.ago)).thenReturn("ago")
        `when`(context.getString(R.string.monthInSingular)).thenReturn("month")
        `when`(context.getString(R.string.monthsInPlural)).thenReturn("months")
        `when`(context.getString(R.string.dayInSingular)).thenReturn("day")
        `when`(context.getString(R.string.daysInPlural)).thenReturn("days")
        `when`(context.getString(R.string.hourMark)).thenReturn("h")
        `when`(context.getString(R.string.minMark)).thenReturn("min")
    }

    @Test
    fun `when less that minute, should return 1min ago`() {
        val startDate = "2000-01-01T12:00:00Z"
        val endDate = "2000-01-01T12:00:32Z"
        val expected = "1 min ago"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `should return 5 min ago`() {
        val startDate = "2000-01-01T12:00:00Z"
        val endDate = "2000-01-01T12:05:00Z"
        val expected = "5 min ago"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `should return 3h ago`() {
        val startDate = "2000-01-01T12:00:00Z"
        val endDate = "2000-01-01T15:00:00Z"
        val expected = "3h ago"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `should return 23h ago, because its the same day`() {
        val startDate = "2000-01-01T00:00:00Z"
        val endDate = "2000-01-01T23:00:00Z"
        val expected = "23h ago"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `should return 1 day ago, when 15 hours ago and different day`() {
        val startDate = "2000-01-01T12:00:00Z"
        val endDate = "2000-01-02T03:00:00Z"
        val expected = "1 day ago"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `should return 1 day ago`() {
        val startDate = "2000-01-01T12:00:00Z"
        val endDate = "2000-01-02T23:23:00Z"
        val expected = "1 day ago"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `should return 1 month ago`() {
        val startDate = "2000-01-01T12:00:00Z"
        val endDate = "2000-02-01T12:00:00Z"
        val expected = "1 month ago"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `should return normal date, because months period is too large`() {
        val startDate = "2000-03-05T12:00:00Z"
        val endDate = "2000-08-01T12:00:00Z"
        val expected = "5/3/2000"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `should format day in plural`() {
        val startDate = "2000-01-01T12:00:00Z"
        val endDate = "2000-01-05T12:00:00Z"
        val expected = "4 days ago"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `should format month in plural`() {
        val startDate = "2000-01-01T12:00:00Z"
        val endDate = "2000-03-01T12:00:00Z"
        val expected = "2 months ago"
        val result = humanDate.timePeriod(startDate, endDate)
        assertThat(result, `is`(expected))
    }
}