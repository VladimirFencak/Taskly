package com.example.taskly.feature_agenda.domain.use_case

import com.example.taskly.core.domain.errors.ErrorDefault
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchFullAgendaUseCase @Inject constructor(
    private val repository: AgendaRepository
) {
    suspend fun execute(): Result<Unit, ErrorDefault> {
        return withContext(Dispatchers.IO) {
            val remoteResult = repository.getFullAgendaRemote()
            when (remoteResult) {
                is Result.Success -> repository.insertTasksLocal(remoteResult.data.tasks)
                is Result.Error -> Result.Error(remoteResult.error)
            }
        }
    }
}