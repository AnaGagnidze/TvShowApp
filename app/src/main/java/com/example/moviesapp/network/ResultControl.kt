package com.example.moviesapp.network

data class ResultControl<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val refreshing: Boolean = false
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ResultControl<T> {
            return ResultControl(Status.SUCCESS, data)
        }

        fun <T> error(message: String): ResultControl<T> {
            return ResultControl(Status.ERROR, null, message)
        }

        fun <T> loading(refreshing: Boolean): ResultControl<T> {
            return ResultControl(Status.LOADING, null, null, refreshing)
        }
    }

}
