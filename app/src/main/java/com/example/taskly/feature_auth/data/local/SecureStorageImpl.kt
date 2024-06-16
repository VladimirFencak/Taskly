package com.example.taskly.feature_auth.data.local

import androidx.security.crypto.EncryptedSharedPreferences
import javax.inject.Inject

class SecureStorageImpl @Inject constructor(
    private val encryptedSharedPreferences: EncryptedSharedPreferences
) : SecureStorage {

    override fun storeJwtToken(token: String) {
        encryptedSharedPreferences.edit().putString("jwt_token", token).apply()
    }

    override fun getJwtToken(): String? {
        return encryptedSharedPreferences.getString("jwt_token", null)
    }
}