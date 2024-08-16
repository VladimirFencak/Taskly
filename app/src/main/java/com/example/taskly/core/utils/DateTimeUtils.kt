package com.example.taskly.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtils {

    fun getStartAndEndOfTodayInUTC(timezone: String): Pair<Long, Long> {
        val timeZone = TimeZone.of(timezone)
        val currentDate = Clock.System.now().toLocalDateTime(timeZone).date

        val startOfDay = currentDate.atStartOfDayIn(timeZone).toEpochMilliseconds()

        val endOfDay = currentDate.atTime(23, 59, 59).toInstant(timeZone).toEpochMilliseconds()

        return Pair(startOfDay, endOfDay)
    }
}