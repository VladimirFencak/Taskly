package com.example.taskly.feature_auth.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.taskly.feature_auth.data.local.SecureStorage
import com.example.taskly.feature_auth.data.local.SecureStorageImpl
import com.example.taskly.feature_auth.data.remote.AuthApi
import com.example.taskly.feature_auth.data.remote.AuthApiImpl
import com.example.taskly.feature_auth.data.repository.AuthRepositoryImpl
import com.example.taskly.feature_auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideAuthRepository(secureStorage: SecureStorage, authApi: AuthApi): AuthRepository {
        return AuthRepositoryImpl(secureStorage, authApi)
    }

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): EncryptedSharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

    @Provides
    @Singleton
    fun provideSecureStorage(encryptedSharedPreferences: EncryptedSharedPreferences): SecureStorage {
        return SecureStorageImpl(encryptedSharedPreferences)
    }
}