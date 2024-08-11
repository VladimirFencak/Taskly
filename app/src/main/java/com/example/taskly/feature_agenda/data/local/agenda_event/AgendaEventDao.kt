package com.example.taskly.feature_agenda.data.local.agenda_event

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaEventDao {

    @Transaction
    suspend fun insertWholeEvent(
        event: AgendaEventEntity,
        attendees: List<AttendeeEntity>,
        photos: List<PhotoEntity>
    ) {
        insertEvent(event)
        insertAttendees(attendees)
        insertPhotos(photos)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: AgendaEventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendees(attendees: List<AttendeeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<PhotoEntity>)

    @Transaction
    @Query("SELECT * FROM agenda_event")
    fun getAllEventsWithDetails(): Flow<List<AgendaEventWithDetails>>

    @Delete
    suspend fun deleteEvent(event: AgendaEventEntity)
}