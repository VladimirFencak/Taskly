package com.example.taskly.feature_auth.data.local

interface SecureStorage {
    fun storeJwtToken(token: String)
    fun getJwtToken(): String?
}