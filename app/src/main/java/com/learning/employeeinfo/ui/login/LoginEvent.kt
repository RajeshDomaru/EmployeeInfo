package com.learning.employeeinfo.ui.login

import com.learning.employeeinfo.data.api.dto.UserDto
import com.learning.employeeinfo.util.sealed.UiText

sealed class LoginEvent {

    data class LoginViewError(val loginError: LoginError) : LoginEvent()

    data class Success(val userDto: UserDto) : LoginEvent()

    object Loading : LoginEvent()

    data class Error(val uiText: UiText, val errorCode: Int? = null) : LoginEvent()

    data class Message(val uiText: UiText) : LoginEvent()

}
