package com.learning.employeeinfo.util.core

import com.learning.employeeinfo.util.EMPTY
import com.learning.employeeinfo.util.sealed.UiText

data class FieldError(val uiText: UiText = EMPTY, val isValidPreviousView: Boolean = false)
