package com.example.taskly.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtils {

    fun getStartAndEndOfTodayInUTC(
        date: LocalDate? = null
    ): Pair<Long, Long> {
        val timeZone = TimeZone.currentSystemDefault()
        val actualDate = date ?: Clock.System.now().toLocalDateTime(timeZone).date

        val startOfDay = actualDate.atStartOfDayIn(timeZone).toEpochMilliseconds()

        val endOfDay = actualDate.atTime(23, 59, 59).toInstant(timeZone).toEpochMilliseconds()

        return Pair(startOfDay, endOfDay)
    }
}