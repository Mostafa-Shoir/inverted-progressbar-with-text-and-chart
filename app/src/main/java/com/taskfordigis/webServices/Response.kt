package com.taskfordigis.webServices

class Response<T> private constructor(val status: String, val data: T?, val message: String?) {
    enum class Status(var status: String) {
        SUCCESS("200"), ERROR("0"), LOADING("1"), EMPTY("201")

    }

    companion object {
        fun <T> success(data: T): Response<T> {
            return Response(
                Status.SUCCESS.status,
                data,
                null
            )
        }

        fun <T> error(message: String?): Response<T> {
            return Response(
                Status.ERROR.status,
                null,
                message
            )
        }

        fun <T> loading(): Response<T> {
            return Response(
                Status.LOADING.status,
                null,
                null
            )
        }

        fun <T> empty(message: String?): Response<T> {
            return Response(
                Status.EMPTY.status,
                null,
                message
            )
        }

    }
}