package com.example.taskly.feature_agenda.di

import android.content.Context
import androidx.room.Room
import com.example.taskly.core.domain.session.UserSessionManager
import com.example.taskly.feature_agenda.data.local.AgendaDatabase
import com.example.taskly.feature_agenda.data.remote.AgendaApi
import com.example.taskly.feature_agenda.data.remote.AgendaApiImpl
import com.example.taskly.feature_agenda.data.repository.AgendaRepositoryImpl
import com.example.taskly.feature_agenda.domain.repository.AgendaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgendaModule {

    @Provides
    @Singleton
    fun provideAgendaApi(client: HttpClient, userSessionManager: UserSessionManager): AgendaApi = AgendaApiImpl(client, userSessionManager)

    @Provides
    @Singleton
    fun provideAgendaRepository(agendaApi: AgendaApi): AgendaRepository = AgendaRepositoryImpl(agendaApi)

    @Provides
    @Singleton
    fun provideAgendaDatabase(
        @ApplicationContext context: Context
    ): AgendaDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AgendaDatabase::class.java,
            "agenda_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAgendaTaskDao(agendaDatabase: AgendaDatabase) = agendaDatabase.agendaTaskDao()

    @Provides
    @Singleton
    fun provideAgendaEventDao(agendaDatabase: AgendaDatabase) = agendaDatabase.agendaEventDao()

    @Provides
    @Singleton
    fun provideAgendaReminderDao(agendaDatabase: AgendaDatabase) = agendaDatabase.agendaReminderDao()

}
