package com.example.taskly.feature_agenda.domain.use_case

import com.example.taskly.core.utils.DateTimeUtils
import com.example.taskly.feature_agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class GetAgendaUseCase @Inject constructor(
    private val repository: AgendaRepository
) {
    suspend fun execute(date: LocalDate? = null) {
        withContext(Dispatchers.IO) {
            val (startOfDayUtc, endOfDayUtc) = DateTimeUtils.getStartAndEndOfTodayInUTC(date)
            repository.getAgendaLocal(startOfDayUtc, endOfDayUtc)
        }
    }
}