package com.learning.employeeinfo.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.learning.employeeinfo.data.api.ApiResponse
import com.learning.employeeinfo.data.api.EmployeeInfoApiClient
import com.learning.employeeinfo.data.api.dto.LoginRequestDto
import com.learning.employeeinfo.data.api.receiveNetworkCall
import com.learning.employeeinfo.util.core.FieldError
import com.learning.employeeinfo.util.sealed.UiEvent
import com.learning.employeeinfo.util.validateEmailAddress
import com.learning.employeeinfo.util.validatePassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent get() = _uiEvent.receiveAsFlow()

    private var _uiEvent1 = MutableSharedFlow<UiEvent>()
    val uiEvent1 get() = _uiEvent1.asSharedFlow()

    private fun login(loginRequestDto: LoginRequestDto): Flow<LoginEvent> = flow {

        val (isValidInputs, loginError) = checkInputs(loginRequestDto)

        if (isValidInputs) {

            val response =
                EmployeeInfoApiClient.client().signIn(loginRequestDto).receiveNetworkCall()

            with(response) {
                when (this) {
                    is ApiResponse.EmptyData -> {}
                    is ApiResponse.Failure -> {

                    }
                    is ApiResponse.Success -> {
                        emit(LoginEvent.Success(data.user))
                    }
                }
            }

        } else {

            emit(LoginEvent.LoginViewError(loginError))

        }

    }

    private fun checkInputs(loginRequestDto: LoginRequestDto): Pair<Boolean, LoginError> {

        val context = getApplication<Application>().applicationContext

        var isValid = true

        val emailUiText = loginRequestDto.email_address.validateEmailAddress(true)

        val passwordUiText = loginRequestDto.password.validatePassword(true)

        var emailFieldError = FieldError()
        if (emailUiText.isNotEmpty(context)) {
            emailFieldError = FieldError(emailUiText, isValid)
            isValid = false
        }

        var passwordFieldError = FieldError()
        if (passwordUiText.first.isNotEmpty(context)) {
            passwordFieldError = FieldError(passwordUiText.first, isValid)
            isValid = false
        }

        return Pair(isValid, LoginError(emailFieldError, passwordFieldError))

    }

}