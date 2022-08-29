package com.learning.employeeinfo.data.api

sealed class ApiResource<out R> {

    data class Success<out R>(val value: R) : ApiResource<R>()

    data class Failure(
        val statusCode: Int = 0,
        val errorMessage: String = "Something went wrong!"
    ) : ApiResource<Nothing>()

    object EmptyData : ApiResource<Nothing>()

}