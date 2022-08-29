package com.learning.employeeinfo.data.api

import com.learning.employeeinfo.util.extensions.validateString
import com.learning.employeeinfo.util.sealed.UiText

fun <T> NetworkResponse<T, ErrorResponse>.receiveNetworkCall(): ApiResponse<T> {

    return when (this) {

        is NetworkResponse.ApiError -> {

            try {

                ApiResponse.Failure(code, UiText.DynamicString(body.message))

            } catch (e: Exception) {

                e.printStackTrace()

                ApiResponse.Failure(code)

            }

        }

        is NetworkResponse.NetworkError -> {

            try {

                ApiResponse.Failure(
                    hashCode(),
                    UiText.DynamicString(error.message.validateString())
                )

            } catch (e: Exception) {

                e.printStackTrace()

                ApiResponse.Failure(hashCode())

            }

        }

        is NetworkResponse.Success -> ApiResponse.Success(body)

        is NetworkResponse.UnknownError -> {

            try {

                ApiResponse.Failure(
                    error.hashCode(),
                    UiText.DynamicString(error?.message.validateString())
                )

            } catch (e: Exception) {

                e.printStackTrace()

                ApiResponse.Failure(hashCode())

            }

        }

        is NetworkResponse.EmptyResponse -> ApiResponse.EmptyData

    }

}
