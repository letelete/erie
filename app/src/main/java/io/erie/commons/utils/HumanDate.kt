package io.erie.commons.utils

import android.content.Context
import io.erie.R
import org.threeten.bp.*
import javax.inject.Inject

class HumanDate @Inject constructor(context: Context) {

    private companion object {
        private const val ZULU_TIMEZONE = "Z"
        private const val MAX_READABLE_MONTHS_DELAY = 3
    }

    private val ago: String by lazy { context.getString(R.string.ago) }
    private val monthInSingular: String by lazy { context.getString(R.string.monthInSingular) }
    private val monthsInPlural: String by lazy { context.getString(R.string.monthsInPlural) }
    private val dayInSingular: String by lazy { context.getString(R.string.dayInSingular) }
    private val daysInPlural: String by lazy { context.getString(R.string.daysInPlural) }
    private val hourMark: String by lazy { context.getString(R.string.hourMark) }
    private val minMark: String by lazy { context.getString(R.string.minMark) }

    private val zonedId = ZoneId.of(ZULU_TIMEZONE)

    private lateinit var startDateTime: LocalDateTime
    private lateinit var endDateTime: LocalDateTime

    private fun currentDateTime() = LocalDateTime.now(zonedId)

    fun timePeriod(startDateText: String, endDateText: String? = null): String {
        startDateTime = convertTextToDateTime(startDateText)
        endDateTime = if (endDateText != null) {
            convertTextToDateTime(endDateText)
        } else {
            currentDateTime()
        }

        val startDate = LocalDate.from(startDateTime)
        val endDate = LocalDate.from(endDateTime)
        val datePeriod = Period.between(startDate, endDate)
        val isPeriodUnreadable = datePeriod.months > MAX_READABLE_MONTHS_DELAY
        when {
            isPeriodUnreadable -> {
                val beautifiedStartDate =
                    "${startDate.dayOfMonth}/${startDate.monthValue}/${startDate.year}"
                return beautifiedStartDate
            }
            datePeriod.months > 0 -> {
                val months = datePeriod.months
                return "$months ${formatForPlural(months, monthInSingular)} $ago"
            }
            datePeriod.days > 0 -> {
                val days = datePeriod.days
                return "$days ${formatForPlural(days, dayInSingular)} $ago"
            }
        }

        val startTime = LocalTime.from(startDateTime)
        val endTime = LocalTime.from(endDateTime)
        val timeDuration = Duration.between(startTime, endTime)
        return when {
            timeDuration.toHours() > 0 -> {
                val hours = timeDuration.toHours()
                "$hours$hourMark $ago"
            }
            timeDuration.toMinutes() > 0 -> {
                val minutes = timeDuration.toMinutes()
                "$minutes $minMark $ago"
            }
            else -> "1 $minMark $ago"
        }
    }

    private fun formatForPlural(period: Int, termInSingular: String): String {
        return when (termInSingular) {
            dayInSingular -> when {
                period > 1 -> daysInPlural
                else -> dayInSingular
            }
            monthInSingular -> when {
                period > 1 -> monthsInPlural
                else -> monthInSingular
            }
            else -> error("No such term")
        }
    }

    private fun convertTextToDateTime(textDateTime: String): LocalDateTime {
        val instantDateTime = Instant.parse(textDateTime)
        return LocalDateTime.ofInstant(instantDateTime, zonedId)
    }
}