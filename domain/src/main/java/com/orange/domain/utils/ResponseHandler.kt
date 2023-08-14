package com.orange.domain.utils

sealed class ResponseHandler<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResponseHandler<T>(data)
    class Error<T>(message: String?, data: T? = null) : ResponseHandler<T>(data, message)
}
