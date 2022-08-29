package com.learning.employeeinfo.ui.login

import com.learning.employeeinfo.util.core.FieldError

data class LoginError(val emailFieldError: FieldError, val passwordFieldError: FieldError)
