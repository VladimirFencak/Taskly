package com.example.taskly.feature_agenda.data.local.agenda_task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: AgendaTaskEntity)

    @Query("SELECT * FROM agenda_task")
    fun getAllTasks(): Flow<List<AgendaTaskEntity>>

    @Query("SELECT * FROM agenda_task WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: String): AgendaTaskEntity?

    @Delete
    suspend fun deleteTask(task: AgendaTaskEntity)
}