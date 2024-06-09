package com.example.taskly.feature_auth.domain.errors

enum class NetworkError : ErrorDefault {
    REQUEST_TIMEOUT,
    NO_INTERNET,
    RECEIVED_SERVER_ERROR_MESSAGE,
    UNAUTHORIZED,           //401
    FORBIDDEN,              //403
    NOT_FOUND,              //404
    INTERNAL_SERVER_ERROR,  //500
    UNKNOWN_ERROR;

    var errorMessage: String? = null
}