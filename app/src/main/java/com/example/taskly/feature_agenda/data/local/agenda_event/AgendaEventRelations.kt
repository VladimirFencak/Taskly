package com.example.taskly.feature_agenda.data.local.agenda_event

import androidx.room.Embedded
import androidx.room.Relation
import com.example.taskly.feature_agenda.domain.model.AgendaEvent
import com.example.taskly.feature_agenda.domain.model.Attendee
import com.example.taskly.feature_agenda.domain.model.Photo

data class AgendaEventWithDetails(
    @Embedded val event: AgendaEventEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "eventId"
    )
    val attendees: List<AttendeeEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "eventId"
    )
    val photos: List<PhotoEntity>
)

fun AgendaEventWithDetails.toAgendaEvent(): AgendaEvent {
    return AgendaEvent(
        id = event.id,
        title = event.title,
        description = event.description,
        from = event.from,
        to = event.to,
        remindAt = event.remindAt,
        host = event.host,
        isUserEventCreator = event.isUserEventCreator,
        attendees = attendees.map {
            Attendee(
                email = it.email,
                fullName = it.fullName,
                userId = it.userId,
                eventId = it.eventId,
                isGoing = it.isGoing,
                remindAt = it.remindAt
            )
        },
        photos = photos.map {
            Photo(
                key = it.key,
                url = it.url
            )
        }
    )
}