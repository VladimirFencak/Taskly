package com.example.taskly.feature_agenda.domain.use_case

import com.example.taskly.feature_agenda.domain.repository.AgendaRepository
import javax.inject.Inject

class GetAgendaUseCase @Inject constructor(
    private val repository: AgendaRepository
) {

}