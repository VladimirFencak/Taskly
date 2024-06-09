package com.example.taskly.feature_auth.di

import com.example.taskly.feature_auth.data.remote.AuthApi
import com.example.taskly.feature_auth.data.remote.AuthApiImpl
import com.example.taskly.feature_auth.data.repository.AuthRepositoryImpl
import com.example.taskly.feature_auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(client: HttpClient): AuthApi = AuthApiImpl(client)

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepositoryImpl(authApi)
    }
}