package com.example.taskly.di

import android.util.Log
import com.example.taskly.BuildConfig
import com.example.taskly.core.domain.session.UserSessionManager
import com.example.taskly.feature_auth.data.UserSessionManagerImpl
import com.example.taskly.feature_auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.e("HttpClient", message)
                    }
                }
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
            }
            install(DefaultRequest) {
                header("x-api-key", BuildConfig.apiKey)
            }
        }
    }

    @Provides
    @Singleton
    fun provideUserSessionManager(authRepository: AuthRepository): UserSessionManager {
        return UserSessionManagerImpl(authRepository)
    }
}