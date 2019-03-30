package io.erie.commons.utils

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class ReadTimeTest {
    @Test
    fun `5050 chars with average word length = 5,3 should return 3`() {
        val content =
            "LSJ columnist Graham Couch, Freep beat writer Chris Solari and Freep columnist Shawn Windsor break down MSU's Sweet 16 win Graham Couch and Chris Solari and Shawn Windsor, Lansing State Journal WASHINGTON — Breaking down Sunday’s East Region Elite Eight game … [+5006 chars]"
        val expectedReadTimeInMinutes = 3
        val readTimeInMinutes = ReadTime.calculate(content)
        MatcherAssert.assertThat(
            readTimeInMinutes,
            CoreMatchers.`is`(expectedReadTimeInMinutes)
        )
    }

    @Test
    fun `should return 1 when no content`() {
        val content = ""
        val expectedReadTimeInMinutes = 1
        val readTimeInMinutes = ReadTime.calculate(content)
        MatcherAssert.assertThat(
            readTimeInMinutes,
            CoreMatchers.`is`(expectedReadTimeInMinutes)
        )
    }

    @Test
    fun `should return 1 when content read time is less than 1`() {
        val content = "Aaaa bbb ccccc ddddd ... [+30 chars]"
        val expectedReadTimeInMinutes = 1
        val readTimeInMinutes = ReadTime.calculate(content)
        MatcherAssert.assertThat(
            readTimeInMinutes,
            CoreMatchers.`is`(expectedReadTimeInMinutes)
        )
    }
}