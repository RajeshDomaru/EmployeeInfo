package com.learning.employeeinfo.data.api

import com.learning.employeeinfo.R
import com.learning.employeeinfo.util.sealed.UiText

sealed class ApiResponse<out T> {

    data class Success<T>(val data: T) : ApiResponse<T>()

    class Failure<T>(
        val errorCode: Int,
        val uiText: UiText = UiText.StringResource(R.string.something_went_wrong)
    ) : ApiResponse<T>()

    object EmptyData : ApiResponse<Nothing>()

}
