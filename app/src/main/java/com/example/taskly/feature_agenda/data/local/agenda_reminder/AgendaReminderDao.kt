package com.example.taskly.feature_agenda.data.local.agenda_reminder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: AgendaReminderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminders(reminders: List<AgendaReminderEntity>)

    @Query("SELECT * FROM agenda_reminder")
    fun getAllReminders(): Flow<List<AgendaReminderEntity>>

    @Query("SELECT * FROM agenda_reminder WHERE time >= :startOfDayUtc AND time <= :endOfDayUtc")
    fun getReminders(startOfDayUtc: Long, endOfDayUtc: Long): Flow<List<AgendaReminderEntity>>

    @Query("SELECT * FROM agenda_reminder WHERE id = :reminderId LIMIT 1")
    suspend fun getReminderById(reminderId: String): AgendaReminderEntity?

    @Delete
    suspend fun deleteReminder(reminder: AgendaReminderEntity)
}