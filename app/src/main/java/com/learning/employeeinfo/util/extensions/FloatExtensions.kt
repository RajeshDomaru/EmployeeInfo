package com.learning.employeeinfo.util.extensions

import com.learning.employeeinfo.util.constants.AMOUNT_FORMAT
import com.learning.employeeinfo.util.constants.DECIMAL_FORMAT

fun Float?.validateFloat(isDefault: Boolean = false): String = when {
    isDefault -> String.format(DECIMAL_FORMAT, this)
    this != null -> if (isDefault) String.format(DECIMAL_FORMAT, this) else ""
    else -> ""
}

fun Float?.isValidFloat(): Boolean = validateFloat(false).isValidString()

fun Float?.validateFloat(): Float = if (this != null && isValidFloat()) toFloat() else 0F

fun Float?.validateAmount(): String = String.format(AMOUNT_FORMAT, this)
