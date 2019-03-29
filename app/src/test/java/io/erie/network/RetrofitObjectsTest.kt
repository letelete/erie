package io.erie.network

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RetrofitObjectsTest {

    @Test
    fun `should return comma-separated words`() {
        val listOfSources = listOf(
            "a",
            "b",
            "c",
            "d"
        )
        val expected = "a,b,c,d"
        val sources = Sources(listOfSources)
        val sourcesToString = sources.toString()
        assertThat(sourcesToString, `is`(expected))
    }

}