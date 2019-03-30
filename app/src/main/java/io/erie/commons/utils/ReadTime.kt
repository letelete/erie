package io.erie.commons.utils

class ReadTime {
    companion object {
        private const val MIN_READ_TIME = 1
        private const val AVERAGE_WPM = 285
        /**
         * Because of the free newsapi.org plan, our article content is limited
         * It means it looks like this:
         *
         * Lorem ipsum, lorem ipsum. lorem …           [+8692 chars]
         * |_______ visible words _______|  |_additional__characters counter_|
         *
         * We are calculating average characters length per visible word
         * and use this value to calculate read time
         */
        fun calculate(content: String): Int {
            val counterRegex = Regex("\\[\\+(.+) chars]")
            val counterMatcher = counterRegex.find(content)
            var charsAmount = counterMatcher?.destructured?.component1()?.toInt()
                ?: return 1

            val visibleCharsAmount = content.filter { !it.isWhitespace() }.length
            charsAmount += visibleCharsAmount
            val visibleWords = content.split(" ")
            val averageWordLength = visibleCharsAmount.toDouble() / visibleWords.count().toDouble()

            val wordsAmount = charsAmount / averageWordLength
            val readTime = (wordsAmount / AVERAGE_WPM).toInt()
            return when {
                readTime > MIN_READ_TIME -> readTime
                else -> MIN_READ_TIME
            }
        }
    }
}