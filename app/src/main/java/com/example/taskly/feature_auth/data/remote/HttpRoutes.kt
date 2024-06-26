package com.example.taskly.feature_auth.data.remote

object HttpRoutes {

    private const val BASE_URL = "https://tasky.pl-coding.com"

    const val REGISTER = "$BASE_URL/register"
    const val LOGIN = "$BASE_URL/login"
    const val LOGOUT = "$BASE_URL/logout"
    const val AUTHENTICATE = "$BASE_URL/authenticate"
}