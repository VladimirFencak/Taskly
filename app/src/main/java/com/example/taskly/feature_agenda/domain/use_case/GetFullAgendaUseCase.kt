package com.example.taskly.feature_agenda.domain.use_case

import com.example.taskly.core.domain.errors.ErrorDefault
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_agenda.domain.model.AgendaResponse
import com.example.taskly.feature_agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFullAgendaUseCase @Inject constructor(
    private val repository: AgendaRepository,
) {
    suspend fun execute(): Result<Flow<AgendaResponse>, ErrorDefault> {
        return withContext(Dispatchers.IO) {
            val localData = repository.getFullAgendaLocal()
            when (localData) {
                is Result.Success -> Result.Success(localData.data)
                is Result.Error -> Result.Error(localData.error)
            }
        }
    }
}