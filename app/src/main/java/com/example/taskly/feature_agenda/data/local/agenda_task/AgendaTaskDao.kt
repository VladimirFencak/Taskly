package com.example.taskly.feature_agenda.data.local.agenda_task

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: AgendaTaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<AgendaTaskEntity>)

    @Query("SELECT * FROM agenda_task")
    fun getAllTasks(): Flow<List<AgendaTaskEntity>>

    @Query("SELECT * FROM agenda_task WHERE id = :taskId LIMIT 1")
    fun getTaskById(taskId: String): Flow<AgendaTaskEntity?>

    @Query("DELETE FROM agenda_task WHERE id = :taskId")
    suspend fun deleteTask(taskId: String)
}