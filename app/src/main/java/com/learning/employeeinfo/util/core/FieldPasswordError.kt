package com.learning.employeeinfo.util.core

import com.learning.employeeinfo.util.EMPTY
import com.learning.employeeinfo.util.sealed.UiText

data class FieldPasswordError(
    var passwordFieldError: Pair<UiText, Boolean> = Pair(EMPTY, false),
    var isValidPreviousView: Boolean = false
)
