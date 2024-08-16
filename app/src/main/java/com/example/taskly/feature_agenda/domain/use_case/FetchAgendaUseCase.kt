package com.example.taskly.feature_agenda.domain.use_case

import com.example.taskly.core.domain.errors.ErrorDefault
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_agenda.domain.model.AgendaRequest
import com.example.taskly.feature_agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchAgendaUseCase @Inject constructor(
    private val repository: AgendaRepository
) {
    suspend fun execute(agendaRequest: AgendaRequest): Result<Unit, ErrorDefault> {
        return withContext(Dispatchers.IO) {
            val remoteResult = repository.getAgendaRemote(agendaRequest)
            when (remoteResult) {
                is Result.Success -> {
                    repository.insertTasksLocal(remoteResult.data.tasks)
                }

                is Result.Error -> {
                    Result.Error(remoteResult.error)
                    //TODO: WorkManager to sync
                }
            }
        }
    }
}