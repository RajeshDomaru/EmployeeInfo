package com.learning.employeeinfo.util.sealed

sealed class UiDialog {

    data class PasswordError(val password: String) : UiDialog()

    data class Api(val uiText: UiText, val errorCode: Int? = null, val isChild: Boolean = false) :
        UiDialog()

    data class Success(val uiSuccess: UiSuccess) : UiDialog()

}
