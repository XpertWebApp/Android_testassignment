package com.app.peyza.networks

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Status
) {
    class Success<T>(data: T?, msg: String?, status: Status) :
        Resource<T>(data = data, message = msg, code = status)

    class Loading<T>(status: Status) : Resource<T>(code = status)
    class Pending<T>(status: Status) : Resource<T>(code = status)
    class Error<T>(message: String, status: Status) : Resource<T>(
        message = message,
        code = status
    )
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    Pending
}

