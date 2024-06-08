package com.example.taskly.auth.data.remote

object HttpRoutes {

    private const val BASE_URL = "https://tasky.pl-coding.com"

    const val REGISTER = "$BASE_URL/register"
    const val LOGIN = "$BASE_URL/login"
    const val LOGOUT = "$BASE_URL/logout"
}